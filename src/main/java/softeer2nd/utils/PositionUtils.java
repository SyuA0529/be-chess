package softeer2nd.utils;

import softeer2nd.chess.board.Board;
import softeer2nd.chess.exception.OutOfBoardException;
import softeer2nd.chess.exception.WrongPositionException;

public class PositionUtils {
    private PositionUtils() {
    }

    public static int getColumnNumFromPos(String position) {
        if(position.length() != 2 || !Character.isAlphabetic(position.charAt(0)))
            throw new WrongPositionException();
        int num = Character.toLowerCase(position.charAt(0)) - 'a';
        verifyPos(num);
        return num;
    }

    public static int getRankNumFromPos(String position) {
        if(position.length() != 2 || !Character.isDigit(position.charAt(1)))
            throw new WrongPositionException();
        int num = Character.getNumericValue(position.charAt(1)) - 1;
        verifyPos(num);
        return num;
    }

    private static void verifyPos(int num) {
        if(num < 0 || num >= Board.SIDE_LENGTH) throw new OutOfBoardException();
    }
}
