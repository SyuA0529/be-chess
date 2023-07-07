package softeer2nd.chess.pieces;

import java.util.List;

public class Rook extends Piece {
    protected Rook(Color color, Position position) {
        super(color, Type.ROOK, position);
    }

    @Override
    protected List<Direction> getMovableDirection() {
        return Direction.linearDirection();
    }

    @Override
    protected boolean isDirectionEqual(Position targetPos, Direction direction) {
        int dx = targetPos.getColumnDiff(targetPos);
        int dy = targetPos.getRankDiff(targetPos);

        return ((dx == 0 && direction.getXDegree() == dx && direction.getYDegree() == dy / Math.abs(dy)) ||
                (dy == 0 && direction.getXDegree() == dx / Math.abs(dx) && direction.getYDegree() == dy));
    }
}
