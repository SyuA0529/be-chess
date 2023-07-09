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
    protected boolean isMovablePositionByDirection(Position targetPos, Direction direction) {
        int dx = targetPos.getFileDiff(getPosition());
        int dy = targetPos.getRankDiff(getPosition());

        return Direction.isMoveDiagonal(direction, dx, dy) || Direction.isMoveLinear(direction, dx, dy);
    }
}
