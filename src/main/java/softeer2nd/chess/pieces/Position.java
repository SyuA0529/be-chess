package softeer2nd.chess.pieces;

import java.util.Objects;

import static softeer2nd.utils.PositionUtils.*;

public class Position {
    private int rankNum;
    private int rowNum;

    public Position(String pos) {
        this(getRankNumFromPos(pos), getRowNumFromPos(pos));
    }

    public Position(int rankNum, int rowNum) {
        this.rankNum = rankNum;
        this.rowNum = rowNum;
    }

    public void changePos(Position targetPos) {
        this.rankNum = targetPos.getRankNum();
        this.rowNum = targetPos.getRowNum();
    }

    public int getRankNum() {
        return rankNum;
    }

    public int getRowNum() {
        return rowNum;
    }

    public int getRankDiff(Position position) {
        return this.getRankNum() - position.getRankNum();
    }

    public int getRowDiff(Position position) {
        return this.getRowNum() - position.getRowNum();
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
