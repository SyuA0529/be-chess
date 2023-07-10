package softeer2nd.chess.pieces;

import softeer2nd.chess.exception.MoveBlankException;

import java.util.List;

public class Blank extends Piece {
    public Blank(Position position) {
        super(Color.NOCOLOR, Type.NO_PIECE, position);
    }

    @Override
    protected List<Direction> getMovableDirection() {
        throw new MoveBlankException();
    }

    @Override
    protected boolean isMovablePositionByDirection(Position targetPos, Direction direction) {
        throw new MoveBlankException();
    }
}
