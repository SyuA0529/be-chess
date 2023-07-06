package softeer2nd.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.exception.OutOfBoardException;

import static org.assertj.core.api.Assertions.*;

class PositionUtilsTest {
    @Test
    @DisplayName("위치에서 RANK 번호 추출")
    void extractRankNumByPosition() {
        //given
        String position = "b8";

        //when
        int rankNum = PositionUtils.getRankNumFromPos(position);

        //then
        assertThat(rankNum).isEqualTo(Character.getNumericValue(position.charAt(1)) - 1);
    }

    @Test
    @DisplayName("위치에서 세로 번호 추출")
    void extractRowNumByPosition() {
        //given
        String position = "b8";

        //when
        int rowNum = PositionUtils.getRowNumFromPos(position);

        //then
        assertThat(rowNum).isEqualTo(position.charAt(0) - 'a');
    }

    @Test
    @DisplayName("체스판을 벗어나는 위치가 들어오면 에러가 발생한다")
    void errorWhenPosOutBoard() {
        String[] errorPoses = {"a0", "a9", "z3", "i0"};
        for (String errorPose : errorPoses) {
            assertThatThrownBy(() -> {
                PositionUtils.getRankNumFromPos(errorPose);
                PositionUtils.getRowNumFromPos(errorPose);
            }).isInstanceOf(OutOfBoardException.class);
        }
    }
}