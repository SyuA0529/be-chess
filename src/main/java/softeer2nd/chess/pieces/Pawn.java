package softeer2nd.chess.pieces;

public class Pawn {
    public static final String WHITE_COLOR = "white";
    public static final String BLACK_COLOR = "black";
    public static final String REPRESENTATION = "p";

    private final String color;

    public Pawn() {
        this.color = WHITE_COLOR;
    }

    public Pawn(String color) {
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }

    public String getRepresentation() {
        return getColor().equals(WHITE_COLOR) ? REPRESENTATION.toLowerCase() : REPRESENTATION.toUpperCase();
    }
}
