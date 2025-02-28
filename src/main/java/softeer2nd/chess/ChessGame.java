package softeer2nd.chess;

import softeer2nd.chess.board.Board;
import softeer2nd.chess.exception.*;
import softeer2nd.chess.pieces.Direction;
import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Piece.Color;
import softeer2nd.chess.pieces.Piece.Type;
import softeer2nd.chess.pieces.Position;

import java.util.List;
import java.util.function.Supplier;
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
        verifyPositionColor(sourcePos, turnColor, IllegalTurnException::new);
        verifyCheckSituation(sourcePiece, targetPos);

        movePiece(sourcePos, targetPos, sourcePiece);
        this.turnColor = getEnemyColor();
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

    private void movePiece(Position sourcePos, Position targetPos, Piece sourcePiece) {
        sourcePiece.changePosition(targetPos);
        board.putPiece(targetPos, sourcePiece);
        board.putPiece(sourcePos, createBlank(sourcePos));
    }

    private void verifyMoveCondition(Piece curPiece, Position targetPos) {
        if (curPiece.isBlank()) {
            throw new MoveBlankException();
        }
        verifyTargetPosition(curPiece, targetPos);
        verifyPathPositions(targetPos, curPiece);
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
            verifyPositionColor(checkPosition, Color.NOCOLOR, IllegalMovePositionException::new);
        }
    }

    private void verifyPositionColor(Position checkPosition, Color color,
                                     Supplier<? extends ChessException> exceptionSupplier) {
        if (!board.findPiece(checkPosition).isColor(color)) {
            throw exceptionSupplier.get();
        }
    }

    private void verifyCheckSituation(Piece sourcePiece, Position afterMovePosition) {
        Position kingPosition = getKingPosition(sourcePiece, afterMovePosition);
        for (Piece enemyPiece : board.getPiecesByColor(getEnemyColor())) {
            try {
                if (enemyPiece.isPosition(afterMovePosition)) {
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

    private void verifyEnemyAttackKing(Piece sourcePiece, Position afterMovePosition,
                                       Position kingPosition, Piece enemyPiece) {
        for (Position enemyMovePosition : enemyPiece.getMovePath(kingPosition)) {
            if (!board.findPiece(enemyMovePosition).isColor(Color.NOCOLOR) && sourcePiece.isPosition(enemyMovePosition)) {
                continue;
            }
            if (board.findPiece(enemyMovePosition).isColor(Color.NOCOLOR) && afterMovePosition.equals(enemyMovePosition)) {
                throw new IllegalMovePositionException();
            }
            verifyPositionColor(enemyMovePosition, Color.NOCOLOR, IllegalMovePositionException::new);
        }
        throw new CheckException();
    }

    private void checkPawnMoveRule(Piece sourcePiece, Position targetPos) {
        Direction moveDirection = sourcePiece.getMoveDirection(targetPos);
        Color checkColor = Color.NOCOLOR;
        if (Direction.diagonalDirection().contains(moveDirection)) {
            checkColor = getEnemyColor();
        }
        verifyPositionColor(targetPos, checkColor, IllegalMovePositionException::new);
    }

    private Color getEnemyColor() {
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
