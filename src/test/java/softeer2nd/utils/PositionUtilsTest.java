package softeer2nd.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.exception.OutOfBoardException;
import softeer2nd.chess.exception.WrongPositionException;

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
        int columnNum = PositionUtils.getColumnNumFromPos(position);

        //then
        assertThat(columnNum).isEqualTo(position.charAt(0) - 'a');
    }

    @Test
    @DisplayName("체스판을 벗어나는 위치가 들어오면 에러가 발생한다")
    void errorWhenPosOutBoard() {
        String[] errorPoses = {"a0", "a9", "z3", "i0"};
        for (String errorPose : errorPoses) {
            assertThatThrownBy(() -> {
                PositionUtils.getRankNumFromPos(errorPose);
                PositionUtils.getColumnNumFromPos(errorPose);
            }).isInstanceOf(OutOfBoardException.class);
        }
    }

    @Test
    @DisplayName("잘못된 입력이 들어오면 에러가 발생한다")
    void verifyInput() {
        String[] wrongPosInput = {"a", "1", "b", "1a"};

        for (int i = 0; i < wrongPosInput.length; i += 2) {
            int finalI = i;
            assertThatThrownBy(() -> PositionUtils.getColumnNumFromPos(wrongPosInput[finalI]))
                    .isInstanceOf(WrongPositionException.class);
            assertThatThrownBy(() -> PositionUtils.getRankNumFromPos(wrongPosInput[finalI + 1]))
                    .isInstanceOf(WrongPositionException.class);
        }

        assertThatThrownBy(() -> PositionUtils.getColumnNumFromPos("aasdf912837"))
                .isInstanceOf(WrongPositionException.class);
    }
}