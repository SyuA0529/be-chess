package softeer2nd.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PositionUtilsTest {
    @Test
    @DisplayName("위치에서 RANK 번호 추출")
    void extractRankNumByPosition() {
        //given
        String position = "b8";

        //when
        int rankNum = PositionUtils.getRankNumFromPosition(position);

        //then
        assertThat(rankNum).isEqualTo(Character.getNumericValue(position.charAt(1)) - 1);
    }

    @Test
    @DisplayName("위치에서 세로 번호 추출")
    void extractRowNumByPosition() {
        //given
        String position = "b8";

        //when
        int rowNum = PositionUtils.getRowNumFromPosition(position);

        //then
        assertThat(rowNum).isEqualTo(position.charAt(0) - 'a');
    }
}