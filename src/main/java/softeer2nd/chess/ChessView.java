package softeer2nd.chess;

import softeer2nd.chess.board.Board;
import softeer2nd.utils.StringUtils;

public class ChessView {
    private final Board board;

    public ChessView(Board board) {
        this.board = board;
    }

    public String showBoard() {
        StringBuilder boardStr = new StringBuilder();
        for (int rankNum = Board.SIDE_LENGTH - 1; rankNum >= 0; rankNum--) {
            StringBuilder sb = new StringBuilder();
            board.getRank(rankNum).forEach(p -> sb.append(p.getRepresentation()));
            boardStr.append(StringUtils.appendNewLine(sb.toString()));
        }
        return boardStr.toString();
    }
}
