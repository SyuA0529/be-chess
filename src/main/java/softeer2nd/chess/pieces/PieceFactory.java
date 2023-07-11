package softeer2nd.chess.pieces;


import softeer2nd.chess.exception.IllegalCreatePieceException;

import static softeer2nd.chess.pieces.Piece.*;

public class PieceFactory {
    private PieceFactory() {
    }

    public static Piece createNotBlank(Color color, Type type, Position position) {
        switch (type) {
            case KING:
                return new King(color, position);
            case QUEEN:
                return new Queen(color, position);
            case ROOK:
                return new Rook(color, position);
            case BISHOP:
                return new Bishop(color, position);
            case KNIGHT:
                return new Knight(color, position);
            case PAWN:
                return new Pawn(color, position);
            default:
                throw new IllegalCreatePieceException();
        }
    }

    public static Piece createBlank(Position position) {
        return new Blank(position);
    }
}
