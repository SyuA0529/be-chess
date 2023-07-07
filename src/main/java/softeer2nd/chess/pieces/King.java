package softeer2nd.chess.pieces;

import java.util.List;

public class King extends Piece {
    protected King(Color color, Position position) {
        super(color, Type.KING, position);
    }

    @Override
    protected List<Direction> getMovableDirection() {
        return Direction.everyDirection();
    }

    @Override
    protected boolean isDirectionEqual(Position targetPos, Direction direction) {
        return direction.getYDegree() == targetPos.getRankDiff(getPosition()) &&
                direction.getXDegree() == targetPos.getColumnDiff(getPosition());
    }
}
