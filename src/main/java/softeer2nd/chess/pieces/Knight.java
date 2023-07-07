package softeer2nd.chess.pieces;

import java.util.List;

public class Knight extends Piece {
    public Knight(Color color, Position position) {
        super(color, Type.KNIGHT, position);
    }

    @Override
    protected List<Direction> getMovableDirection() {
        return Direction.knightDirection();
    }

    @Override
    protected boolean isDirectionEqual(Position targetPos, Direction direction) {
        return direction.getYDegree() == targetPos.getRankDiff(getPosition()) &&
                direction.getYDegree() == targetPos.getColumnDiff(getPosition());
    }
}
