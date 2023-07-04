package softeer2nd.chess.pieces;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

public class PawnTest {

    @Test
    @DisplayName("흰색 폰과 검은색 폰이 생성되어야 한다")
    void create() {
        String[] colors = {Pawn.WHITE_COLOR, Pawn.BLACK_COLOR};
        for (String color : colors)
            verifyPawn(new Pawn(color), color);
    }

    @Test
    @DisplayName("기본 생성자로 폰 생성시 흰색")
    void createWhitePawnWithDefaultConstructor() {
        Pawn pawn = new Pawn();
        assertThat(pawn.getColor()).isEqualTo(Pawn.WHITE_COLOR);
    }

    private static void verifyPawn(Pawn pawn, String color) {
        assertThat(pawn.getColor()).isEqualTo(color);
    }
}
