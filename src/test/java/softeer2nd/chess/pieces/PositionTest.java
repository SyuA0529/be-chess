package softeer2nd.chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.exception.OutOfBoardException;

import static org.assertj.core.api.Assertions.*;

class PositionTest {
    @Test
    @DisplayName("체스 판을 벗어나는 위치면 에러가 발생한다")
    void errorWhenPosOutBoard() {
        String[] errorPoses = {"a0", "a9", "z3", "i0"};
        for (String errorPose : errorPoses) {
            assertThatThrownBy(() -> new Position(errorPose))
                    .isInstanceOf(OutOfBoardException.class);
        }

        Position notErrorPos = new Position("a1");
        for (String errorPose : errorPoses) {
            assertThatThrownBy(() -> notErrorPos.changePos(errorPose))
                    .isInstanceOf(OutOfBoardException.class);
        }
    }
}