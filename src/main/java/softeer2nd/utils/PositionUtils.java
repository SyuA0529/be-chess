package softeer2nd.utils;

public class PositionUtils {
    private PositionUtils() {
    }

    public static int getRowNumFromPosition(String position) {
        return Character.toLowerCase(position.charAt(0)) - 'a';
    }

    public static int getRankNumFromPosition(String position) {
        return Character.getNumericValue(position.charAt(1)) - 1;
    }
}