package softeer2nd.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.board.Board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static softeer2nd.utils.StringUtils.appendNewLine;


class ChessViewTest {
    Board board;
    ChessView chessView;

    @BeforeEach
    void setUp() {
        board = new Board();
        chessView = new ChessView(board);
    }

    @Test
    @DisplayName("체스판 출력 테스트")
    void printBoard() {
        board.initialize();
        assertEquals(32, board.countTotalPieces());
        String blankRank = appendNewLine("........");
        assertEquals(
                appendNewLine("RNBQKBNR") +
                        appendNewLine("PPPPPPPP") +
                        blankRank + blankRank + blankRank + blankRank +
                        appendNewLine("pppppppp") +
                        appendNewLine("rnbqkbnr"),
                chessView.showBoard());
    }

}