package softeer2nd.chess.pieces;

import java.util.List;

import static softeer2nd.chess.pieces.Direction.*;

public class King extends Piece {
    protected King(Color color, Position position) {
        super(color, Type.KING, position);
    }

    @Override
    protected List<Direction> getMovableDirection() {
        return everyDirection();
    }

    @Override
    protected boolean isMovablePositionByDirection(Position targetPos, Direction direction) {
        return isMoveOnceToDirection(direction, targetPos.getFileDiff(getPosition()), targetPos.getRankDiff(getPosition()));
    }
}
