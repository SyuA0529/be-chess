package softeer2nd.chess.pieces;

import softeer2nd.utils.PositionUtils;

import java.util.Objects;

import static softeer2nd.utils.PositionUtils.*;

public class Position {
    private int rankNum;
    private int rowNum;

    public Position(String pos) {
        this(getRankNumFromPosition(pos), getRowNumFromPosition(pos));
    }

    private Position(int rankNum, int rowNum) {
        this.rankNum = rankNum;
        this.rowNum = rowNum;
    }

    public void changePos(String targetPos) {
        this.rankNum = getRankNumFromPosition(targetPos);
        this.rowNum = getRowNumFromPosition(targetPos);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return rankNum == position.rankNum && rowNum == position.rowNum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rankNum, rowNum);
    }
}
