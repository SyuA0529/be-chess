package softeer2nd.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


class StringUtilsTest {
    @Nested
    @DisplayName("appendNewLine method")
    class AppendNewLine {
        @Test
        @DisplayName("개행 문자를 뒤에 추가해야 한다")
        void addNewLineToString() {
            //given
            String str = "string";

            //when
            String resultStr = StringUtils.appendNewLine(str);

            //then
            assertThat(resultStr).isEqualTo(str + StringUtils.NEWLINE);
        }
    }
}
