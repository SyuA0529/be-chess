package softeer2nd.chess.exception;

public class WrongPositionException extends RuntimeException {
    private static final String MESSAGE = "입력한 위치를 해석할 수 없습니다";

    public WrongPositionException() {
        super(MESSAGE);
    }
}
