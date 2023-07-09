package softeer2nd.chess.exception;

public class IllegalMovePieceException extends RuntimeException {
    private static final String MESSAGE = "해당 기물은 움직일 수 없습니다";

    public IllegalMovePieceException() {
        super(MESSAGE);
    }
}
