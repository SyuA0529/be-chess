package softeer2nd.chess.exception;

public class CheckmateException extends ChessException {
    private static final String MESSAGE = "체크메이트 입니다.";

    public CheckmateException() {
        super(MESSAGE);
    }
}
