package softeer2nd.chess.pieces;

import softeer2nd.chess.exception.IllegalMovePieceException;

import java.util.List;

public class Blank extends Piece {
    public Blank(Position position) {
        super(Color.NOCOLOR, Type.NO_PIECE, position);
    }

    @Override
    protected List<Direction> getMovableDirection() {
        throw new IllegalMovePieceException();
    }

    @Override
    protected boolean isMovablePositionByDirection(Position targetPos, Direction direction) {
        throw new IllegalMovePieceException();
    }
}
