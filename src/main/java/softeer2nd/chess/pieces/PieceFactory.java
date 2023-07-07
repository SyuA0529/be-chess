package softeer2nd.chess.pieces;


import softeer2nd.chess.exception.CannotCreatePieceException;

import static softeer2nd.chess.pieces.Piece.*;

public class PieceFactory {
    public static Piece createNotBlank(Color color, Type type, Position position) {
        if(type.equals(Type.KING)) return new King(color, position);
        if(type.equals(Type.QUEEN)) return new Queen(color, position);
        if(type.equals(Type.ROOK)) return new Rook(color, position);
        if(type.equals(Type.BISHOP)) return new Bishop(color, position);
        if(type.equals(Type.KNIGHT)) return new Knight(color, position);
        if(type.equals(Type.PAWN)) return new Pawn(color, position);
        throw new CannotCreatePieceException();
    }

    public static Piece createBlank(Position position) {
        return new Blank(position);
    }
}
