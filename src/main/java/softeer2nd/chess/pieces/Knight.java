package softeer2nd.chess.pieces;

import java.util.List;

import static softeer2nd.chess.pieces.Direction.*;

public class Knight extends Piece {
    protected Knight(Color color, Position position) {
        super(color, Type.KNIGHT, position);
    }

    @Override
    protected List<Direction> getMovableDirection() {
        return knightDirection();
    }

    @Override
    protected boolean isMovablePositionByDirection(Position targetPos, Direction direction) {
        return isMoveOnceToDirection(direction,
                targetPos.getFileDiff(getPosition()),
                targetPos.getRankDiff(getPosition()));
    }
}
