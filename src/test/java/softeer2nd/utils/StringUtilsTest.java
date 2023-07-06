package softeer2nd.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


class StringUtilsTest {
    @Test
    @DisplayName("개행문자 추가")
    void addNewLineToString() {
        //given
        String str = "string";

        //when
        String resultStr = StringUtils.appendNewLine(str);

        //then
        assertThat(resultStr).isEqualTo(str + StringUtils.NEWLINE);
    }
}