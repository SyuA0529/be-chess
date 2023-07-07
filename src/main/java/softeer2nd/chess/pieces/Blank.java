package softeer2nd.chess.pieces;

import softeer2nd.chess.exception.CannotMovePieceException;

import java.util.List;

public class Blank extends Piece {
    public Blank(Position position) {
        super(Color.NOCOLOR, Type.NO_PIECE, position);
    }

    @Override
    protected List<Direction> getMovableDirection() {
        throw new CannotMovePieceException();
    }

    @Override
    protected boolean isDirectionEqual(Position position, Direction direction) {
        throw new CannotMovePieceException();
    }
}
