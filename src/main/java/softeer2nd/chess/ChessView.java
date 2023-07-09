package softeer2nd.chess;

import softeer2nd.chess.board.Board;

public class ChessView {
    private final Board board;

    public ChessView(Board board) {
        this.board = board;
    }

    public String showBoard() {
        return board.toString();
    }
}
