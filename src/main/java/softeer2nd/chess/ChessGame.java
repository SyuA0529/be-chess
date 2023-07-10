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

import java.util.ArrayList;
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
        Piece curPiece = board.findPiece(sourcePos);

        verifyMoveCondition(curPiece, targetPos, new ArrayList<>());
        verifyTurn(curPiece.getPosition());
        verifyCheckmate(curPiece, targetPos);

        curPiece.changePosition(targetPos);
        board.putPiece(targetPos, curPiece);
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

    private void verifyMoveCondition(Piece curPiece, Position targetPos, List<Position> ignorePositions) {
        verifySourcePiece(curPiece, ignorePositions);
        verifyTargetPosition(curPiece, targetPos);
        verifyPathPositions(targetPos, curPiece, ignorePositions);
    }

    private void verifySourcePiece(Piece curPiece, List<Position> ignorePositions) {
        if (curPiece.isBlank()) {
            throw new MoveBlankException();
        }
        if(ignorePositions.contains(curPiece.getPosition())) {
            throw new IllegalMovePositionException();
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

    private void verifyPathPositions(Position targetPos, Piece sourcePiece, List<Position> ignorePositions) {
        for (Position checkPosition : sourcePiece.getMovePath(targetPos)) {
            verifyPathPosition(targetPos, checkPosition, ignorePositions);
        }
    }

    private void verifyPathPosition(Position targetPos, Position checkPosition, List<Position> ignorePositions) {
        if (!checkPosition.equals(targetPos) &&
                (!board.findPiece(checkPosition).isBlank() || ignorePositions.contains(checkPosition))) {
            throw new IllegalMovePositionException();
        }
    }

    private void verifyTurn(Position sourcePos) {
        if (!board.findPiece(sourcePos).isColor(turnColor)) {
            throw new IllegalTurnException();
        }
    }

    private void verifyCheckmate(Piece sourcePiece, Position targetPos) {
        List<Position> ignorePositions = new ArrayList<>();
        Position kingPosition = board.getKingPositionByColor(turnColor);
        if(sourcePiece.isType(Type.KING)) {
            kingPosition = targetPos;
        } else {
            ignorePositions.add(targetPos);
        }

        List<Piece> enemyPieces = board.getPiecesByColor(getEnemyColor());
        for (Piece enemyPiece : enemyPieces) {
            try {
                verifyMoveCondition(enemyPiece, kingPosition, ignorePositions);
                throw new CheckmateException();
            } catch (IllegalMovePositionException ignored) {
            }
        }
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
        if (!board.findPiece(targetPos).isBlank()) {
            throw new IllegalMovePositionException();
        }
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
