package softeer2nd.chess.board;

import softeer2nd.chess.exception.MissingKingException;
import softeer2nd.chess.pieces.Piece.Color;
import softeer2nd.chess.pieces.Piece.Type;
import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.PieceFactory;
import softeer2nd.chess.pieces.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static softeer2nd.chess.board.Board.SIDE_LENGTH;

public class Rank {
    private final List<Piece> pieces = new ArrayList<>(SIDE_LENGTH);

    private Rank() {
    }

    public static Rank getEdgeRank(Color color) {
        Rank rank = new Rank();
        int rankNum = 0;
        if (color.equals(Color.BLACK)) {
            rankNum = 7;
        }

        Type[] types = {Type.ROOK, Type.KNIGHT, Type.BISHOP, Type.QUEEN, Type.KING, Type.BISHOP, Type.KNIGHT, Type.ROOK};
        for (int fileNum = 0; fileNum < SIDE_LENGTH; fileNum++) {
            rank.add(PieceFactory.createNotBlank(color, types[fileNum], new Position(fileNum, rankNum)));
        }
        return rank;
    }

    public static Rank getPawnRank(Color color) {
        Rank rank = new Rank();
        int rankNum = 1;
        if (color.equals(Color.BLACK)) {
            rankNum = 6;
        }

        for (int fileNum = 0; fileNum < SIDE_LENGTH; fileNum++) {
            rank.add(PieceFactory.createNotBlank(color, Type.PAWN, new Position(fileNum, rankNum)));
        }
        return rank;
    }

    public static Rank getBlankRank(int rankNum) {
        Rank rank = new Rank();
        for (int fileNum = 0; fileNum < SIDE_LENGTH; fileNum++) {
            rank.add(PieceFactory.createBlank(new Position(fileNum, rankNum)));
        }
        return rank;
    }

    public Piece get(int fileNum) {
        return pieces.get(fileNum);
    }

    private void add(Piece piece) {
        pieces.add(piece);
    }

    public void set(int fileNum, Piece piece) {
        pieces.set(fileNum, piece);
    }

    public int countTotalPieces() {
        return (int) pieces.stream()
                .filter(p -> !p.isBlank())
                .count();
    }

    public int countSpecificPieces(Color color, Type type) {
        return (int) pieces.stream()
                .filter(p -> p.isColor(color) && p.isType(type))
                .count();
    }

    public Position getKingPosition() {
        for (Piece piece : pieces) {
            if (piece.isType(Type.KING)) {
                return piece.getPosition();
            }
        }
        throw new MissingKingException();
    }

    public List<Piece> getPiecesByColor(Color color) {
        return pieces.stream()
                .filter(p -> p.isColor(color))
                .collect(Collectors.toList());
    }

    public String showRank() {
        StringBuilder sb = new StringBuilder();
        pieces.forEach(p -> sb.append(p.getRepresentation()));
        return sb.toString();
    }
}
