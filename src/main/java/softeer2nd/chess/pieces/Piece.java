package softeer2nd.chess.pieces;

import softeer2nd.chess.board.Board;
import softeer2nd.chess.exception.CannotMovePosException;

import java.util.*;


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

    public List<Position> getMovePath(Position targetPos) {
        if(getType().equals(Type.KING)) return getKingMovePath(targetPos);
        if(getType().equals(Type.QUEEN)) return getQueenMovePath(targetPos);
        return new ArrayList<>();
    }

    private List<Position> getKingMovePath(Position targetPos) {
        Direction direction = Direction.diagonalDirection().stream()
                .filter(d -> d.getYDegree() == targetPos.getRankDiff(position) &&
                        d.getXDegree() == targetPos.getRowDiff(position))
                .findFirst()
                .orElseThrow(CannotMovePosException::new);
        return List.of(new Position(position.getRankNum() + direction.getYDegree(),
                position.getRowNum() + direction.getXDegree()));
    }

    private List<Position> getQueenMovePath(Position targetPos) {
        int dx = targetPos.getRowDiff(position);
        int dy = targetPos.getRankDiff(position);
        Direction direction = Direction.everyDirection().stream()
                .filter(d ->
                        (Math.abs(dx) == Math.abs(dy) && d.getXDegree() == dx / Math.abs(dx) && d.getYDegree() == dy / Math.abs(dy)) ||
                        ((dx == 0 && d.getXDegree() == dx && d.getYDegree() == dy / Math.abs(dy)) ||
                                (dy == 0 && d.getXDegree() == dx / Math.abs(dx) && d.getYDegree() == dy)))
                .findFirst()
                .orElseThrow(CannotMovePosException::new);

        List<Position> positions = new ArrayList<>();
        for (int i = 1; i < Board.SIDE_LENGTH; i++) {
            Position curPos = new Position(position.getRankNum() + direction.getYDegree() * i,
                    position.getRowNum() + direction.getXDegree() * i);
            if(curPos.equals(targetPos)) break;
            positions.add(curPos);
        }
        return positions;
    }

    public void changePosition(Position targetPos) {
        position.changePos(targetPos);
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