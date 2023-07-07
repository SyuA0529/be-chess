package softeer2nd.chess.pieces;

import java.util.Objects;

import static softeer2nd.utils.PositionUtils.*;

public class Position {
    private int rankNum;
    private int columnNum;

    public Position(String pos) {
        this(getRankNumFromPos(pos), getColumnNumFromPos(pos));
    }

    public Position(int rankNum, int columnNum) {
        this.rankNum = rankNum;
        this.columnNum = columnNum;
    }

    public void changePos(Position targetPos) {
        this.rankNum = targetPos.getRankNum();
        this.columnNum = targetPos.getColumnNum();
    }

    public int getRankNum() {
        return rankNum;
    }

    public int getColumnNum() {
        return columnNum;
    }

    public int getRankDiff(Position position) {
        return this.getRankNum() - position.getRankNum();
    }

    public int getColumnDiff(Position position) {
        return this.getColumnNum() - position.getColumnNum();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return rankNum == position.rankNum && columnNum == position.columnNum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rankNum, columnNum);
    }

}
