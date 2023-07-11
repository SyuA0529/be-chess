package softeer2nd.chess;

import softeer2nd.chess.board.Board;
import softeer2nd.chess.exception.CheckmateException;
import softeer2nd.chess.exception.MoveBlankException;
import softeer2nd.chess.exception.IllegalMovePositionException;
import softeer2nd.chess.exception.IllegalTurnException;
import softeer2nd.chess.pieces.Direction;
import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Piece.Color;
import softeer2nd.chess.pieces.Piece.Type;
import softeer2nd.chess.pieces.Position;

import java.util.List;
import java.util.stream.IntStream;

import static softeer2nd.chess.pieces.PieceFactory.*;

public class ChessGame {
    private Color turnColor;
    private final Board board;

    public ChessGame(Board board) {
        this.turnColor = Color.WHITE;
        this.board = board;
    }

    public void movePiece(Position sourcePos, Position targetPos) {
        Piece sourcePiece = board.findPiece(sourcePos);

        verifyMoveCondition(sourcePiece, targetPos);
        verifyTurn(sourcePiece.getPosition());
        verifyCheckmate(sourcePiece, targetPos);

        sourcePiece.changePosition(targetPos);
        board.putPiece(targetPos, sourcePiece);
        board.putPiece(sourcePos, createBlank(sourcePos));
        changeTurn();
    }

    public double calculatePoint(Color color) {
        return IntStream.range(0, Board.SIDE_LENGTH)
                .mapToDouble(file -> calculateFilePoint(file, color))
                .sum();
    }

    private double calculateFilePoint(int fileNum, Color color) {
        List<Piece> file = board.getPiecesInFileByColor(fileNum, color);
        double point = file.stream().mapToDouble(Piece::getDefaultPoint).sum();
        long pawnCount = getPawnCount(file);

        if (pawnCount > 1) {
            point -= (Piece.Type.PAWN.getDefaultPoint() / 2) * pawnCount;
        }
        return point;
    }

    private void changeTurn() {
        this.turnColor = getEnemyColor();
    }

    private void verifyMoveCondition(Piece curPiece, Position targetPos) {
        verifySourcePiece(curPiece);
        verifyTargetPosition(curPiece, targetPos);
        verifyPathPositions(targetPos, curPiece);
    }

    private void verifySourcePiece(Piece curPiece) {
        if (curPiece.isBlank()) {
            throw new MoveBlankException();
        }
    }

    private void verifyTargetPosition(Piece sourcePiece, Position targetPos) {
        if (targetPos.equals(sourcePiece.getPosition())) {
            throw new IllegalMovePositionException();
        }
        if (board.findPiece(targetPos).isColor(sourcePiece.getColor())) {
            throw new IllegalMovePositionException();
        }
        if (sourcePiece.isType(Type.PAWN)) {
            checkPawnMoveRule(sourcePiece, targetPos);
        }
    }

    private void verifyPathPositions(Position targetPos, Piece sourcePiece) {
        for (Position checkPosition : sourcePiece.getMovePath(targetPos)) {
            verifyPathPosition(checkPosition);
        }
    }

    private void verifyPathPosition(Position checkPosition) {
        if (!board.findPiece(checkPosition).isBlank()) {
            throw new IllegalMovePositionException();
        }
    }

    private void verifyTurn(Position sourcePos) {
        if (!board.findPiece(sourcePos).isColor(turnColor)) {
            throw new IllegalTurnException();
        }
    }

    private void verifyCheckmate(Piece sourcePiece, Position afterMovePosition) {
        Position kingPosition = getKingPosition(sourcePiece, afterMovePosition);
        for (Piece enemyPiece : board.getPiecesByColor(getEnemyColor())) {
            try {
                if(enemyPiece.getPosition().equals(afterMovePosition)) {
                    continue;
                }
                verifyEnemyAttackKing(sourcePiece, afterMovePosition, kingPosition, enemyPiece);
            } catch (IllegalMovePositionException ignored) {
            }
        }
    }

    private Position getKingPosition(Piece sourcePiece, Position afterMovePosition) {
        Position kingPosition = board.getKingPositionByColor(turnColor);
        if (sourcePiece.isType(Type.KING)) {
            kingPosition = afterMovePosition;
        }
        return kingPosition;
    }

    private void verifyEnemyAttackKing(Piece sourcePiece, Position afterMovePosition, Position kingPosition, Piece enemyPiece) {
        for (Position enemyMovePosition : enemyPiece.getMovePath(kingPosition)) {
            //이동하는 경로에 장애물이 있을 때 기존에는 존재했으나 사라졌다면
            if(!board.findPiece(enemyMovePosition).isBlank() && sourcePiece.getPosition().equals(enemyMovePosition)) {
                continue;
            }
            //기존에는 장애물이 존재하지 않았으나 생겼다면
            if(board.findPiece(enemyMovePosition).isBlank() && afterMovePosition.equals(enemyMovePosition)) {
                throw new IllegalMovePositionException();
            }
            verifyPathPosition(enemyMovePosition);
        }
        throw new CheckmateException();
    }

    private void checkPawnMoveRule(Piece sourcePiece, Position targetPos) {
        Direction moveDirection = sourcePiece.getMoveDirection(targetPos);
        if (Direction.linearDirection().contains(moveDirection)) {
            checkPawnMoveLinearRule(targetPos);
        }
        if (Direction.diagonalDirection().contains(moveDirection)) {
            checkPawnMoveDiagonalRule(targetPos);
        }
    }

    private void checkPawnMoveDiagonalRule(Position targetPos) {
        Color enemyColor = getEnemyColor();
        if (!board.findPiece(targetPos).isColor(enemyColor)) {
            throw new IllegalMovePositionException();
        }
    }

    private void checkPawnMoveLinearRule(Position targetPos) {
        verifyPathPosition(targetPos);
    }

    public Color getEnemyColor() {
        Color enemyColor = Color.WHITE;
        if (turnColor.equals(Color.WHITE)) {
            enemyColor = Color.BLACK;
        }
        return enemyColor;
    }

    private static int getPawnCount(List<Piece> file) {
        return (int) file.stream().filter(p -> p.isType(Type.PAWN)).count();
    }
}
