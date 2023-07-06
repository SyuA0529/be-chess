package softeer2nd.chess.pieces;

import softeer2nd.chess.exception.CannotMovePosException;
import java.util.Objects;

import static softeer2nd.utils.PositionUtils.*;

public class Piece implements Comparable<Piece>{
    private final Color color;
    private final Type type;
    private final Position position;

    private Piece(Color color, Type type, Position position) {
        this.color = color;
        this.type = type;
        this.position = position;
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

    public static Piece createBlank(Position position) {
        return new Piece(Color.NOCOLOR, Type.NO_PIECE, position);
    }

    private static Piece createWhite(Type type, Position position) {
        return new Piece(Color.WHITE, type, position);
    }


    private static Piece createBlack(Type type, Position position) {
        return new Piece(Color.BLACK, type, position);
    }

    public static Piece createWhitePawn(Position position) {
        return createWhite(Type.PAWN, position);
    }

    public static Piece createWhiteKnight(Position position) {
        return createWhite(Type.KNIGHT, position);
    }

    public static Piece createWhiteRook(Position position) {
        return createWhite(Type.ROOK, position);
    }

    public static Piece createWhiteBishop(Position position) {
        return createWhite(Type.BISHOP, position);
    }

    public static Piece createWhiteQueen(Position position) {
        return createWhite(Type.QUEEN, position);
    }

    public static Piece createWhiteKing(Position position) {
        return createWhite(Type.KING, position);
    }

    public static Piece createBlackPawn(Position position) {
        return createBlack(Type.PAWN, position);
    }

    public static Piece createBlackKnight(Position position) {
        return createBlack(Type.KNIGHT, position);
    }

    public static Piece createBlackRook(Position position) {
        return createBlack(Type.ROOK, position);
    }

    public static Piece createBlackBishop(Position position) {
        return createBlack(Type.BISHOP, position);
    }

    public static Piece createBlackQueen(Position position) {
        return createBlack(Type.QUEEN, position);
    }

    public static Piece createBlackKing(Position position) {
        return createBlack(Type.KING, position);
    }

    public boolean isWhite() {
        return getColor().equals(Color.WHITE);
    }

    public boolean isBlack() {
        return getColor().equals(Color.BLACK);
    }

    public boolean isBlank() {
        return type.equals(Type.NO_PIECE);
    }

    public void move(String targetPos) {
        if(type.equals(Type.KING)) verifyKingMove(targetPos);
        position.changePos(targetPos);
    }

    public void verifyKingMove(String targetPos) {
        if(getRankNumDiff(targetPos) > 1 || getRowNumDiff(targetPos) > 1)
            throw new CannotMovePosException();
    }

    private int getRowNumDiff(String targetPos) {
        return Math.abs(position.getRowNum() - getRowNumFromPos(targetPos));
    }

    private int getRankNumDiff(String targetPos) {
        return Math.abs(position.getRankNum() - getRankNumFromPos(targetPos));
    }

    @Override
    public int compareTo(Piece other) {
        return Double.compare(this.getType().getDefaultPoint(), other.getType().getDefaultPoint());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return color == piece.color && type == piece.type && Objects.equals(position, piece.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, type, position);
    }

    public enum Color {
        WHITE, BLACK, NOCOLOR
    }

    public enum Type {
        PAWN('p', 1), ROOK('r', 5.0),
        KNIGHT('n', 2.5), BISHOP('b', 3),
        QUEEN('q', 9), KING('k', 0),
        NO_PIECE('.', 0);

        private final char representation;
        private final double defaultPoint;

        Type(char representation, double defaultPoint) {
            this.representation = representation;
            this.defaultPoint = defaultPoint;
        }

        public char getWhiteRepresentation() {
            return representation;
        }

        public char getBlackRepresentation() {
            return Character.toUpperCase(representation);
        }

        public double getDefaultPoint() {
            return defaultPoint;
        }
    }
}