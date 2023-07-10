package softeer2nd.chess.exception;

public class MoveBlankException extends RuntimeException {
    private static final String MESSAGE = "빈 공간은 움직일 수 없습니다";

    public MoveBlankException() {
        super(MESSAGE);
    }
}
