package softeer2nd.chess.pieces;

import softeer2nd.chess.board.Board;
import softeer2nd.chess.exception.CannotMovePosException;

import java.util.*;


public abstract class Piece implements Comparable<Piece>{
    private final Color color;
    private final Type type;
    private final Position position;

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
        if(color.equals(Color.BLACK)) return type.getBlackRepresentation();
        return type.getWhiteRepresentation();
    }

    public boolean isColor(Color color) {
        return getColor().equals(color);
    }

    public boolean isBlank() {
        return type.equals(Type.NO_PIECE);
    }

    public List<Position> getMovePath(Position targetPos) {
        Direction direction = getMoveDirection(targetPos);

        List<Position> positions = new ArrayList<>();
        for (int i = 1; i < Board.SIDE_LENGTH; i++) {
            Position curPos = new Position(
                    getPosition().getRankNum() + direction.getYDegree() * i,
                    getPosition().getColumnNum() + direction.getXDegree() * i);
            if(curPos.equals(targetPos)) break;
            positions.add(curPos);
        }
        return positions;
    }

    protected abstract List<Direction> getMovableDirection();

    protected Direction getMoveDirection(Position targetPos) {
        return getMovableDirection().stream()
                .filter(d -> isDirectionEqual(targetPos, d))
                .findFirst()
                .orElseThrow(CannotMovePosException::new);
    }

    protected abstract boolean isDirectionEqual(Position position, Direction direction);

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