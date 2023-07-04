package softeer2nd.utils;

public class StringUtils {
    private StringUtils() {
    }

    public static final String NEWLINE = System.getProperty("line.separator");

    public static final String appendNewLine(String str) {
        return str + NEWLINE;
    }
}
