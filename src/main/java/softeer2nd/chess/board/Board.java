package softeer2nd.chess.board;

import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Piece.Type;
import softeer2nd.chess.pieces.Piece.Color;
import softeer2nd.chess.pieces.PieceFactory;
import softeer2nd.chess.pieces.Position;

import java.util.*;


public class Board {
    public static final int SIDE_LENGTH = 8;

    private final List<Rank> board = new ArrayList<>();

    public void initializeEmpty() {
        board.clear();
        for (int i = 0; i < SIDE_LENGTH; i++)
            initBlankRank(i + 1);
    }

    public void initialize() {
        board.clear();
        initEdgeRank(Color.WHITE);
        initPawnRank(Piece.Color.WHITE);

        for (int i = 0; i < SIDE_LENGTH - 4; i++)
            initBlankRank(i + 3);

        initPawnRank(Piece.Color.BLACK);
        initEdgeRank(Color.BLACK);
    }

    private void initEdgeRank(Color color) {
        Rank rank = new Rank();
        String line = color.equals(Color.WHITE) ? "1" : "8";
        List<Type> types = List.of(Type.ROOK, Type.KNIGHT, Type.BISHOP, Type.QUEEN,
                Type.KING, Type.BISHOP, Type.KNIGHT, Type.ROOK);

        for (int i = 0; i < SIDE_LENGTH; i++)
            rank.add(PieceFactory.createNotBlank(
                    color,
                    types.get(i),
                    new Position(Character.toString('a' + i) + line)));
        board.add(rank);
    }

    private void initPawnRank(Color color) {
        Rank rank = new Rank();
        String line = color.equals(Color.WHITE) ? "2" : "7";
        for (int i = 0; i < SIDE_LENGTH; i++)
            rank.add(PieceFactory.createNotBlank(
                    color,
                    Type.PAWN,
                    new Position(Character.toString('a' + i) + line)));
        board.add(rank);
    }

    private void initBlankRank(int rankNum) {
        Rank rank = new Rank();
        for (int i = 0; i < SIDE_LENGTH; i++)
            rank.add(PieceFactory.createBlank(
                        new Position(Character.toString('a' + i) + rankNum)));
        board.add(rank);
    }

    public Piece findPiece(Position position) {
        return board.get(position.getRankNum())
                .get(position.getColumnNum());
    }

    public void putPiece(Position position, Piece piece) {
        board.get(position.getRankNum()).set(position.getColumnNum(), piece);
    }

    public List<Piece> getColumn(int columnNum) {
        List<Piece> pieces = new ArrayList<>();
        for (int rankNum = 0; rankNum < SIDE_LENGTH; rankNum++)
            pieces.add(board.get(rankNum).get(columnNum));
        return pieces;
    }

    public Rank getRank(int rankNum) {
        return board.get(rankNum);
    }

    public int countTotalPieces() {
        return board.stream().mapToInt(Rank::countTotalPieces).sum();
    }

    public int countSpecificTypePieces(Color color, Type type) {
        return board.stream()
                .mapToInt(r -> r.countSpecificPieces(color, type))
                .sum();
    }

    public List<Piece> getSortedColorPiecesByPoint(Color color) {
        List<Piece> pieces = new ArrayList<>();
        board.forEach(r -> pieces.addAll(r.getSpecificColorPieces(color)));
        pieces.sort(Collections.reverseOrder());
        return pieces;
    }
}
