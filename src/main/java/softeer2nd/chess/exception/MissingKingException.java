package softeer2nd.chess.exception;

public class MissingKingException extends ChessException {
    private static final String MESSAGE = "킹이 존재하지 않습니다.";

    public MissingKingException() {
        super(MESSAGE);
    }
}
