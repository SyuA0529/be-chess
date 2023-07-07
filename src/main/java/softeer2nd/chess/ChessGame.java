package softeer2nd.chess;

import softeer2nd.chess.board.Board;
import softeer2nd.chess.exception.CannotMovePosException;
import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Position;

import java.util.stream.IntStream;

import static softeer2nd.chess.pieces.PieceFactory.*;

public class ChessGame {
    private final Board board;

    public ChessGame(Board board) {
        this.board = board;
    }

    public void movePiece(Position sourcePos, Position targetPos) {
        Piece curPiece = board.findPiece(sourcePos);

        curPiece.getMovePath(targetPos).forEach(pos -> {
            if(!pos.equals(targetPos) && board.findPiece(pos).getType() != Piece.Type.NO_PIECE)
                throw new CannotMovePosException();
        });
        if(sourcePos.equals(targetPos)) throw new CannotMovePosException();
        verifyTargetPosColor(targetPos, curPiece);


        curPiece.changePosition(targetPos);
        board.putPiece(targetPos, curPiece);
        board.putPiece(sourcePos, createBlank(sourcePos));
    }

    public double calculatePoint(Piece.Color color) {
        return IntStream.range(0, Board.SIDE_LENGTH)
                .mapToDouble(column -> calculateColumnPoint(column, color))
                .sum();
    }

    private double calculateColumnPoint(int columnNum, Piece.Color color) {
        int point = 0;
        int pawnCount = 0;
        for (Piece piece : board.getColumn(columnNum)) {
            if(!piece.getColor().equals(color)) continue;
            if(piece.getType().equals(Piece.Type.PAWN)) pawnCount++;
            point += piece.getType().getDefaultPoint();
        }
        return pawnCount > 1 ? point - (Piece.Type.PAWN.getDefaultPoint() / 2) * pawnCount : point;
    }

    private void verifyTargetPosColor(Position targetPos, Piece curPiece) {
        if(board.findPiece(targetPos).getColor().equals(curPiece.getColor()))
            throw new CannotMovePosException();
    }
}
