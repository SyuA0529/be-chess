package softeer2nd.chess.exception;

public class CannotMovePieceException extends RuntimeException {
    private static final String MESSAGE = "해당 기물은 움직일 수 없습니다";

    public CannotMovePieceException() {
        super(MESSAGE);
    }
}
