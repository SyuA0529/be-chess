package softeer2nd.chess.exception;

public class IllegalCreatePieceException extends RuntimeException {
    private static final String MESSAGE = "기물을 생성할 수 없습니다";

    public IllegalCreatePieceException() {
        super(MESSAGE);
    }
}
