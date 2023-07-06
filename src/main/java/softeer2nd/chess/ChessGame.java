package softeer2nd.chess;

import softeer2nd.chess.board.Board;
import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Position;

public class ChessGame {
    private final Board board;

    public ChessGame(Board board) {
        this.board = board;
    }

    public void movePiece(String sourcePos, String targetPos) {
        Piece curPiece = board.findPiece(sourcePos);
        curPiece.move(targetPos);
        board.putPiece(targetPos, curPiece);
        board.putPiece(sourcePos, Piece.createBlank(new Position(sourcePos)));
    }

    public double calculatePoint(Piece.Color color) {
        double point = 0;
        for (int rowNum = 0; rowNum < Board.SIDE_LENGTH; rowNum++) {
            point += calculateRowPoint(rowNum, color);
        }
        return point;
    }

    private double calculateRowPoint(int rowNum, Piece.Color color) {
        int point = 0;
        int pawnCount = 0;
        for (Piece piece : board.getRow(rowNum)) {
            if(!piece.getColor().equals(color)) continue;
            point += piece.getType().getDefaultPoint();
            if(piece.getType().equals(Piece.Type.PAWN)) pawnCount++;
        }
        return pawnCount > 1 ? point - (Piece.Type.PAWN.getDefaultPoint() / 2) * pawnCount : point;
    }
}
