package softeer2nd.chess.pieces;

import softeer2nd.chess.board.Board;
import softeer2nd.chess.exception.IllegalMovePositionException;

import java.util.*;


public abstract class Piece implements Comparable<Piece> {
    private final Color color;
    private final Type type;
    private Position position;

    protected Piece(Color color, Type type, Position position) {
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

    public Position getPosition() {
        return position;
    }

    public char getRepresentation() {
        if (color.equals(Color.BLACK)) return type.getBlackRepresentation();
        return type.getWhiteRepresentation();
    }

    public boolean isPosition(Position position) {
        return getPosition().equals(position);
    }

    public boolean isColor(Color color) {
        return getColor().equals(color);
    }

    public boolean isType(Type type) {
        return getType().equals(type);
    }

    public boolean isBlank() {
        return type.equals(Type.NO_PIECE);
    }

    public List<Position> getMovePath(Position targetPos) {
        Direction direction = getMoveDirection(targetPos);

        List<Position> path = new ArrayList<>();
        for (int moveCount = 1; moveCount < Board.SIDE_LENGTH; moveCount++) {
            Position curPos = new Position(
                    getPosition().getFileNum() + direction.getXDegree() * moveCount,
                    getPosition().getRankNum() + direction.getYDegree() * moveCount);
            if (curPos.equals(targetPos)) {
                break;
            }
            path.add(curPos);
        }
        return path;
    }

    protected abstract List<Direction> getMovableDirection();

    public Direction getMoveDirection(Position targetPos) {
        return getMovableDirection().stream()
                .filter(d -> isMovablePositionByDirection(targetPos, d))
                .findFirst()
                .orElseThrow(IllegalMovePositionException::new);
    }

    protected abstract boolean isMovablePositionByDirection(Position targetPos, Direction direction);

    public void changePosition(Position targetPos) {
        this.position = targetPos;
    }

    public double getDefaultPoint() {
        return getType().getDefaultPoint();
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
