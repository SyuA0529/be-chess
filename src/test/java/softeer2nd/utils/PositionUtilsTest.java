package softeer2nd.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.exception.IllegalInputException;
import softeer2nd.chess.exception.OutOfBoardException;

import static org.assertj.core.api.Assertions.*;

class PositionUtilsTest {
    @Nested
    @DisplayName("getRankNumFromPosition method")
    class GetRankNumFromPosition {
        @Nested
        @DisplayName("체스판 내 위치 문자열이라면")
        class IsStringPositionInBoard {
            @Test
            @DisplayName("위치 문자열로부터 RANK 번호를 반환한다")
            void returnRankNumByPosition() {
                //given
                String position = "b8";

                //when
                int rankNum = PositionUtils.getRankNumFromPosition(position);

                //then
                assertThat(rankNum).isEqualTo(Character.getNumericValue(position.charAt(1) - 1));
            }
        }

        @Nested
        @DisplayName("체스판 밖 위치 문자열이라면")
        class IsStringPositionOutBoard {
            @Test
            @DisplayName("OutOfBoardException이 발생한다")
            void errorWhenPosOutBoard() {
                String[] errorPoses = {"a0", "a9"};
                for (String errorPose : errorPoses) {
                    assertThatThrownBy(() -> PositionUtils.getRankNumFromPosition(errorPose))
                            .isInstanceOf(OutOfBoardException.class);
                }
            }
        }

        @Nested
        @DisplayName("문자열의 길이가 2가 아니거나 문자열의 두 번째 문자가 숫자가 아니라면")
        class IsStringNotMathBoard {
            @Test
            @DisplayName("WrongPositionException이 발생한다")
            void throwWrongPositionException() {
                String[] errorPoses = {"a1b1", "ab"};
                for (String errorPose : errorPoses) {
                    assertThatThrownBy(() -> PositionUtils.getRankNumFromPosition(errorPose))
                            .isInstanceOf(IllegalInputException.class);
                }
            }
        }
    }
    
    @Nested
    @DisplayName("getFileNumFromPosition method")
    class GetFileNumFromPosition {
        @Nested
        @DisplayName("체스판 내 위치 문자열이라면")
        class IsStringPositionInBoard {
            @Test
            @DisplayName("위치 문자열로부터 FILE 번호를 반환한다")
            void returnRowNumByPosition() {
                //given
                String position = "b8";

                //when
                int fileNum = PositionUtils.getFileNumFromPosition(position);

                //then
                assertThat(fileNum).isEqualTo(position.charAt(0) - 'a');
            }
        }

        @Nested
        @DisplayName("체스판 밖 위치 문자열이라면")
        class IsStringPositionOutBoard {
            @Test
            @DisplayName("OutOfBoardException이 발생한다")
            void errorWhenPosOutBoard() {
                String[] errorPoses = {"z1", "i3"};
                for (String errorPose : errorPoses) {
                    assertThatThrownBy(() -> PositionUtils.getFileNumFromPosition(errorPose))
                            .isInstanceOf(OutOfBoardException.class);
                }
            }
        }

        @Nested
        @DisplayName("문자열의 길이가 2가 아니거나 문자열의 첫 번째 문자가 영어가 아니라면")
        class IsStringNotMathBoard {
            @Test
            @DisplayName("WrongPositionException이 발생한다")
            void throwWrongPositionException() {
                String[] errorPoses = {"a1b1", "13"};
                for (String errorPose : errorPoses) {
                    assertThatThrownBy(() -> PositionUtils.getFileNumFromPosition(errorPose))
                            .isInstanceOf(IllegalInputException.class);
                }
            }
        }
    }
}
