package softeer2nd.chess.pieces;

import java.util.List;

public class Bishop extends Piece {
    public Bishop(Color color, Position position) {
        super(color, Type.BISHOP, position);
    }

    @Override
    protected List<Direction> getMovableDirection() {
        return Direction.diagonalDirection();
    }

    @Override
    protected boolean isDirectionEqual(Position targetPos, Direction direction) {
        return false;
    }
}
