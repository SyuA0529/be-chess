package softeer2nd.chess.pieces;

import java.util.List;

import static softeer2nd.chess.pieces.Direction.*;

public class Bishop extends Piece {
    public Bishop(Color color, Position position) {
        super(color, Type.BISHOP, position);
    }

    @Override
    protected List<Direction> getMovableDirection() {
        return diagonalDirection();
    }

    @Override
    protected boolean isMovablePositionByDirection(Position targetPos, Direction direction) {

        return isMoveDiagonal(direction,
                targetPos.getFileDiff(getPosition()),
                targetPos.getRankDiff(getPosition()));
    }
}
