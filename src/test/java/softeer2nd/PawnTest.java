package softeer2nd;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
public class PawnTest {

    @Test
    @DisplayName("흰색 폰과 검은색 폰이 생성되어야 한다")
    void create() {
        String[] colors = {"white", "black"};
        for (String color : colors)
            verifyPawn(new Pawn(color), color);
    }

    private static void verifyPawn(Pawn pawn, String color) {
        Assertions.assertThat(pawn.getColor()).isEqualTo(color);
    }
}
