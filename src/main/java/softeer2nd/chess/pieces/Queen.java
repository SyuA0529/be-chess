package softeer2nd.chess.pieces;

import java.util.List;

public class Queen extends Piece {
    public Queen(Color color, Position position) {
        super(color, Type.QUEEN, position);
    }

    @Override
    protected List<Direction> getMovableDirection() {
        return Direction.everyDirection();
    }

    @Override
    protected boolean isDirectionEqual(Position targetPos, Direction direction) {
        int dx = targetPos.getColumnDiff(getPosition());
        int dy = targetPos.getRankDiff(getPosition());

        return checkDiagonal(direction, dx, dy) || checkLinear(direction, dx, dy);
    }

    private static boolean checkLinear(Direction direction, int dx, int dy) {
        return (dx == 0 && direction.getXDegree() == dx && direction.getYDegree() == dy / Math.abs(dy)) ||
                (dy == 0 && direction.getXDegree() == dx / Math.abs(dx) && direction.getYDegree() == dy);
    }

    private static boolean checkDiagonal(Direction direction, int dx, int dy) {
        return Math.abs(dx) == Math.abs(dy) && Math.abs(dx) > 1 &&
                direction.getXDegree() == dx / Math.abs(dx) &&
                direction.getYDegree() == dy / Math.abs(dy);
    }
}
