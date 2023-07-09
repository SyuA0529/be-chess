package softeer2nd.chess;

import softeer2nd.chess.board.Board;
import softeer2nd.chess.exception.IllegalMovePositionException;
import softeer2nd.chess.pieces.Direction;
import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Piece.Color;
import softeer2nd.chess.pieces.Piece.Type;
import softeer2nd.chess.pieces.Position;

import java.util.List;
import java.util.stream.IntStream;

import static softeer2nd.chess.pieces.PieceFactory.*;

public class ChessGame {
    private final Board board;

    public ChessGame(Board board) {
        this.board = board;
    }

    public void movePiece(Position sourcePos, Position targetPos) {
        Piece curPiece = board.findPiece(sourcePos);

        verifyPathPositions(targetPos, curPiece);
        verifyTargetPosition(targetPos, curPiece);

        curPiece.changePosition(targetPos);
        board.putPiece(targetPos, curPiece);
        board.putPiece(sourcePos, createBlank(sourcePos));
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


    private void verifyPathPositions(Position targetPos, Piece sourcePiece) {
        for (Position pos : sourcePiece.getMovePath(targetPos)) {
            verifyPathPosition(targetPos, pos);
        }
    }

    private void verifyTargetPosition(Position targetPos, Piece sourcePiece) {
        if (targetPos.equals(sourcePiece.getPosition())) {
            throw new IllegalMovePositionException();
        }
        if (board.findPiece(targetPos).isColor(sourcePiece.getColor())) {
            throw new IllegalMovePositionException();
        }
        if(sourcePiece.isType(Type.PAWN)) {
            checkPawnMoveRule(targetPos, sourcePiece);
        }
    }

    private void verifyPathPosition(Position targetPos, Position pos) {
        if (!pos.equals(targetPos) && !board.findPiece(pos).isBlank()) {
            throw new IllegalMovePositionException();
        }
    }

    private void checkPawnMoveRule(Position targetPos, Piece sourcePiece) {
        Direction moveDirection = sourcePiece.getMoveDirection(targetPos);
        if(Direction.linearDirection().contains(moveDirection)){
            checkPawnMoveLinearRule(targetPos);
        }
        if(Direction.diagonalDirection().contains(moveDirection)) {
            checkPawnMoveDiagonalRule(targetPos, sourcePiece);
        }
    }

    private void checkPawnMoveDiagonalRule(Position targetPos, Piece sourcePiece) {
        Color enemyColor = getEnemyColor(sourcePiece);
        if (!board.findPiece(targetPos).isColor(enemyColor)) {
            throw new IllegalMovePositionException();
        }
    }

    private void checkPawnMoveLinearRule(Position targetPos) {
        if (!board.findPiece(targetPos).isBlank()) {
            throw new IllegalMovePositionException();
        }
    }

    public static Color getEnemyColor(Piece sourcePiece) {
        Color enemyColor = Color.WHITE;
        if(sourcePiece.isColor(Color.WHITE)) {
            enemyColor = Color.BLACK;
        }
        return enemyColor;
    }

    private static int getPawnCount(List<Piece> file) {
        return (int) file.stream().filter(p -> p.isType(Type.PAWN)).count();
    }
}
