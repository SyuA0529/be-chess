package softeer2nd.chess.pieces;

import java.util.Objects;

import static softeer2nd.utils.PositionUtils.*;

public class Position {
    private int rankNum;
    private int fileNum;

    public Position(String pos) {
        this(getFileNumFromPosition(pos), getRankNumFromPosition(pos));
    }

    public Position(int fileNum, int rankNum) {
        verifyNumberInBoard(fileNum);
        this.fileNum = fileNum;

        verifyNumberInBoard(rankNum);
        this.rankNum = rankNum;
    }

    public void changePos(Position targetPos) {
        this.rankNum = targetPos.getRankNum();
        this.fileNum = targetPos.getFileNum();
    }

    public int getRankNum() {
        return rankNum;
    }

    public int getFileNum() {
        return fileNum;
    }

    public int getRankDiff(Position position) {
        return this.getRankNum() - position.getRankNum();
    }

    public int getFileDiff(Position position) {
        return this.getFileNum() - position.getFileNum();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return rankNum == position.rankNum && fileNum == position.fileNum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rankNum, fileNum);
    }

}
