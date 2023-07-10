package softeer2nd.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
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
    
    @Nested
    @DisplayName("showBoard method")
    class ChessBoard {
        @Test
        @DisplayName("현재 체스판의 상태를 나타내는 문자열을 반환한다")
        void printChessBoardCorrect() {
            board.initialize();

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
}
