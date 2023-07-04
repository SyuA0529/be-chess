package softeer2nd.chess;

import org.junit.jupiter.api.Test;
import softeer2nd.chess.Board;
import softeer2nd.chess.pieces.Pawn;

import static org.assertj.core.api.Assertions.*;

class BoardTest {
    @Test
    void create() {
        Board board = new Board();
        String[] colors = {Pawn.WHITE_COLOR, Pawn.BLACK_COLOR};

        for (int i = 0; i < colors.length; i++) {
            Pawn pawn = new Pawn(colors[i]);
            board.add(pawn);
            assertThat(board.size()).isEqualTo(i + 1);
            assertThat(board.get(i)).isEqualTo(pawn);
        }
    }

    @Test
    void initialize() {
        Board board = new Board();
        board.initialize();

        assertThat(board.getWhitePawnsResult()).isEqualTo("pppppppp");
        assertThat(board.getBlackPawnsResult()).isEqualTo("PPPPPPPP");
        assertThat(board.print()).isEqualTo(
                "........\n" +
                        "PPPPPPPP\n" +
                        "........\n" +
                        "........\n" +
                        "........\n" +
                        "........\n" +
                        "pppppppp\n" +
                        "........"
        );
    }
}