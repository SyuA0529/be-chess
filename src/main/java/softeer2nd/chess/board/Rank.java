package softeer2nd.chess.board;

import softeer2nd.chess.pieces.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Rank extends ArrayList<Piece> {
    public int countTotalPieces() {
        return (int) this.stream()
                .filter(p -> p.getType() != Piece.Type.NO_PIECE)
                .count();
    }

    public int countSpecificPieces(Piece.Color color, Piece.Type type) {
        return (int) this.stream()
                .filter(p -> p.getColor().equals(color) && p.getType().equals(type))
                .count();
    }

    public List<Piece> getSpecificColorPieces(Piece.Color color) {
        return this.stream()
                .filter(p -> p.getColor().equals(color))
                .collect(Collectors.toList());
    }
}
