package softeer2nd.chess;

import softeer2nd.chess.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

import static softeer2nd.utils.StringUtils.*;

public class Board {
    private static final int SIDE_LENGTH = 8;

    private final List<Rank> board = new ArrayList<>();

    public void initialize() {
        board.clear();
        initBlackEdgeRank();
        initPawnRank(Piece.Color.BLACK);
        for (int i = 0; i < SIDE_LENGTH - 4; i++)
            initBlankRank();
        initPawnRank(Piece.Color.WHITE);
        initWhiteEdgeRank();
    }

    private void initWhiteEdgeRank() {
        Rank rank = new Rank();
        rank.add(Piece.createWhiteRook());
        rank.add(Piece.createWhiteKnight());
        rank.add(Piece.createWhiteBishop());
        rank.add(Piece.createWhiteQueen());
        rank.add(Piece.createWhiteKing());
        rank.add(Piece.createWhiteBishop());
        rank.add(Piece.createWhiteKnight());
        rank.add(Piece.createWhiteRook());
        board.add(rank);
    }

    private void initBlackEdgeRank() {
        Rank rank = new Rank();
        rank.add(Piece.createBlackRook());
        rank.add(Piece.createBlackKnight());
        rank.add(Piece.createBlackBishop());
        rank.add(Piece.createBlackQueen());
        rank.add(Piece.createBlackKing());
        rank.add(Piece.createBlackBishop());
        rank.add(Piece.createBlackKnight());
        rank.add(Piece.createBlackRook());
        board.add(rank);
    }

    private void initPawnRank(Piece.Color color) {
        Rank rank = new Rank();
        for (int i = 0; i < SIDE_LENGTH; i++)
            rank.add(color.equals(Piece.Color.WHITE) ?
                    Piece.createWhitePawn() : Piece.createBlackPawn());
        board.add(rank);
    }

    private void initBlankRank() {
        Rank rank = new Rank();
        for (int i = 0; i < SIDE_LENGTH; i++)
            rank.add(Piece.createBlank());
        board.add(rank);
    }

    public String showBoard() {
        StringBuilder sb = new StringBuilder();
        board.forEach(r -> sb.append(appendNewLine(r.showRank())));
        return sb.toString();
    }

    public int pieceCount() {
        return board.stream().mapToInt(Rank::pieceCount).sum();
    }

    private static class Rank extends ArrayList<Piece> {
        public Piece get(char row) {
            if(!Character.isAlphabetic(row))
                throw new RuntimeException();
            return get(Character.toLowerCase(row) - 'a');
        }

        public String showRank() {
            StringBuilder sb = new StringBuilder();
            this.forEach(p -> sb.append(p.getRepresentation()));
            return sb.toString();
        }

        public int pieceCount() {
            return (int) this.stream().filter(p -> p.getType() != Piece.Type.NO_PIECE).count();
        }
    }
}
