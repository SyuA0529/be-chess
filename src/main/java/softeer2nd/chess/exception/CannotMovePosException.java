package softeer2nd.chess.exception;

public class CannotMovePosException extends RuntimeException{
    private static final String MESSAGE = "해당 위치로 움직일 수 없습니다";
    public CannotMovePosException() {
        super(MESSAGE);
    }
}
