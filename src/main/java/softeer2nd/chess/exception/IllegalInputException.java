package softeer2nd.chess.exception;

public class IllegalInputException extends RuntimeException{
    private static final String MESSAGE = "입력을 해석할 수 없습니다";

    public IllegalInputException() {
        super(MESSAGE);
    }
}
