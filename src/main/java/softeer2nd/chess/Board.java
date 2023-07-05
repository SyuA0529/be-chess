package softeer2nd.chess;

import softeer2nd.chess.pieces.Piece;

import java.util.*;
import java.util.stream.Collectors;

import static softeer2nd.utils.PositionUtils.*;
import static softeer2nd.utils.StringUtils.*;

public class Board {
    private static final int SIDE_LENGTH = 8;

    private final List<Rank> board = new ArrayList<>();

    public void initializeEmpty() {
        board.clear();
        for (int i = 0; i < SIDE_LENGTH; i++)
            initBlankRank();
    }

    public void initialize() {
        board.clear();
        initWhiteEdgeRank();
        initPawnRank(Piece.Color.WHITE);

        for (int i = 0; i < SIDE_LENGTH - 4; i++)
            initBlankRank();

        initPawnRank(Piece.Color.BLACK);
        initBlackEdgeRank();
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
        for (int i = SIDE_LENGTH - 1; i >= 0; i--)
            sb.append(appendNewLine(board.get(i).showRank()));
        return sb.toString();
    }

    public int totalPieceCount() {
        return board.stream().mapToInt(Rank::totalPieceCount).sum();
    }

    public int countSpecificType(Piece.Color color, Piece.Type type) {
        return board.stream()
                .mapToInt(r -> r.countSpecificPiece(color, type))
                .sum();
    }

    public Piece findPiece(String position) {
        return board.get(getRankNumFromPosition(position))
                .get(getRowNumFromPosition(position));
    }

    public void move(String position, Piece piece) {
        board.get(getRankNumFromPosition(position))
                .set(getRowNumFromPosition(position), piece);
    }

    public double calculatePoint(Piece.Color color) {
        double point = 0;

        for (int row = 0; row < SIDE_LENGTH; row++) {
            int curRowPawnCount = 0;
            for (int rankNum = 0; rankNum < SIDE_LENGTH; rankNum++) {
                Piece curPiece = board.get(rankNum).get(row);
                if(!curPiece.getColor().equals(color)) continue;

                point += curPiece.getType().getDefaultPoint();
                if(curPiece.getType().equals(Piece.Type.PAWN)) curRowPawnCount++;
            }
            if(curRowPawnCount > 1) point -= (Piece.Type.PAWN.getDefaultPoint() / 2) * curRowPawnCount;
        }

        return point;
    }

    public List<Piece> getSortedColorPiecesByPoint(Piece.Color color) {
        List<Piece> pieces = new ArrayList<>();
        board.forEach(r -> pieces.addAll(r.getSpecificColorPieces(color)));
        pieces.sort(Collections.reverseOrder());
        return pieces;
    }

    private static class Rank extends ArrayList<Piece> {
        public String showRank() {
            StringBuilder sb = new StringBuilder();
            this.forEach(p -> sb.append(p.getRepresentation()));
            return sb.toString();
        }

        public int totalPieceCount() {
            return (int) this.stream()
                    .filter(p -> p.getType() != Piece.Type.NO_PIECE)
                    .count();
        }

        public int countSpecificPiece(Piece.Color color, Piece.Type type) {
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
}
