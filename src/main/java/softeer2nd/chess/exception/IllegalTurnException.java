package softeer2nd.chess.exception;

public class IllegalTurnException extends ChessException {
    private static final String MESSAGE = "턴이 아닙니다";

    public IllegalTurnException() {
        super(MESSAGE);
    }
}
