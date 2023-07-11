package softeer2nd.chess.pieces;


import java.util.List;

import static softeer2nd.chess.pieces.Direction.*;

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
        if (isColor(Color.WHITE)) return whitePawnDirection();
        return blackPawnDirection();
    }

    @Override
    protected boolean isMovablePositionByDirection(Position targetPos, Direction direction) {
        if (isFirstMove) {
            if (Direction.isMoveLinear(direction, targetPos.getFileDiff(getPosition()), targetPos.getRankDiff(getPosition()))) {
                return Math.abs(targetPos.getRankDiff(getPosition())) <= 2;
            }
        }

        return isMoveOnceToDirection(direction, targetPos.getFileDiff(getPosition()), targetPos.getRankDiff(getPosition()));
    }
}
