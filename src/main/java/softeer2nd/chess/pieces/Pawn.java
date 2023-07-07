package softeer2nd.chess.pieces;

import java.util.List;

public class Pawn extends Piece {
    private boolean isFirstMove = true;
    protected Pawn(Color color, Position position) {
        super(color, Type.PAWN, position);
    }

    @Override
    public void changePosition(Position targetPos) {
        super.changePosition(targetPos);
        isFirstMove = false;
    }

    @Override
    protected List<Direction> getMovableDirection() {
        return isColor(Color.WHITE) ?
                Direction.whitePawnDirection() : Direction.blackPawnDirection();
    }

    @Override
    protected boolean isDirectionEqual(Position targetPos, Direction direction) {
        if(isFirstMove && direction.equals(isColor(Color.WHITE) ? Direction.NORTH : Direction.SOUTH)) {
            return Math.abs(targetPos.getRankDiff(getPosition())) <= 2 && targetPos.getColumnDiff(targetPos) == 0;
        }

        return direction.getYDegree() == targetPos.getRankDiff(getPosition()) &&
                direction.getYDegree() == targetPos.getColumnDiff(getPosition());
    }
}
