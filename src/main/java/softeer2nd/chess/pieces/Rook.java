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
    protected boolean isMovablePositionByDirection(Position targetPos, Direction direction) {
        return Direction.isMoveLinear(direction,
                targetPos.getFileDiff(getPosition()), targetPos.getRankDiff(getPosition()));
    }
}
