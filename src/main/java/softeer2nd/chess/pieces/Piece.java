package softeer2nd.chess.pieces;

public class Piece {
    private final Color color;
    private final Type type;

    private Piece(Color color, Type type) {
        this.color = color;
        this.type = type;
    }

    public Color getColor() {
        return this.color;
    }

    public Type getType() {
        return type;
    }

    public char getRepresentation() {
        if(color.equals(Color.BLACK)) return type.getBlackRepresentation();
        return type.getWhiteRepresentation();
    }

    public static Piece createBlank() {
        return new Piece(Color.NOCOLOR, Type.NO_PIECE);
    }

    private static Piece createWhite(Type type) {
        return new Piece(Color.WHITE, type);
    }

    public static Piece createWhitePawn() {
        return createWhite(Type.PAWN);
    }

    public static Piece createWhiteKnight() {
        return createWhite(Type.KNIGHT);
    }

    public static Piece createWhiteRook() {
        return createWhite(Type.ROOK);
    }

    public static Piece createWhiteBishop() {
        return createWhite(Type.BISHOP);
    }

    public static Piece createWhiteQueen() {
        return createWhite(Type.QUEEN);
    }

    public static Piece createWhiteKing() {
        return createWhite(Type.KING);
    }

    private static Piece createBlack(Type type) {
        return new Piece(Color.BLACK, type);
    }

    public static Piece createBlackPawn() {
        return createBlack(Type.PAWN);
    }

    public static Piece createBlackKnight() {
        return createBlack(Type.KNIGHT);
    }

    public static Piece createBlackRook() {
        return createBlack(Type.ROOK);
    }

    public static Piece createBlackBishop() {
        return createBlack(Type.BISHOP);
    }

    public static Piece createBlackQueen() {
        return createBlack(Type.QUEEN);
    }

    public static Piece createBlackKing() {
        return createBlack(Type.KING);
    }

    public boolean isWhite() {
        return getColor().equals(Color.WHITE);
    }

    public boolean isBlack() {
        return getColor().equals(Color.BLACK);
    }

    public enum Color {
        WHITE, BLACK, NOCOLOR;
    }

    public enum Type {
        PAWN('p'), ROOK('r'), KNIGHT('n'),
        BISHOP('b'), QUEEN('q'), KING('k'), NO_PIECE('.');

        private final char representation;

        Type(char representation) {
            this.representation = representation;
        }

        public char getWhiteRepresentation() {
            return representation;
        }

        public char getBlackRepresentation() {
            return Character.toUpperCase(representation);
        }
    }
}
