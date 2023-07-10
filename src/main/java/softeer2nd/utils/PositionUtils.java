package softeer2nd.utils;

import softeer2nd.chess.board.Board;
import softeer2nd.chess.exception.IllegalInputException;
import softeer2nd.chess.exception.OutOfBoardException;

public class PositionUtils {
    private PositionUtils() {
    }

    public static int getFileNumFromPosition(String position) {
        if (position.length() != 2 || !Character.isAlphabetic(position.charAt(0))) {
            throw new IllegalInputException();
        }
        int num = Character.toLowerCase(position.charAt(0)) - 'a';
        verifyNumberInBoard(num);
        return num;
    }

    public static int getRankNumFromPosition(String position) {
        if (position.length() != 2 || !Character.isDigit(position.charAt(1))) {
            throw new IllegalInputException();
        }
        int num = Character.getNumericValue(position.charAt(1)) - 1;
        verifyNumberInBoard(num);
        return num;
    }

    public static void verifyNumberInBoard(int num) {
        if (num < 0 || num >= Board.SIDE_LENGTH) {
            throw new OutOfBoardException();
        }
    }
}
