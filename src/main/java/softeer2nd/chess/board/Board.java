package softeer2nd.chess.board;

import softeer2nd.chess.exception.CannotMovePosException;
import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Piece.Type;
import softeer2nd.chess.pieces.Piece.Color;
import softeer2nd.chess.pieces.Position;

import java.util.*;

import static softeer2nd.utils.PositionUtils.*;

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
        initWhiteEdgeRank();
        initPawnRank(Piece.Color.WHITE);

        for (int i = 0; i < SIDE_LENGTH - 4; i++)
            initBlankRank(i + 3);

        initPawnRank(Piece.Color.BLACK);
        initBlackEdgeRank();
    }

    private void initWhiteEdgeRank() {
        Rank rank = new Rank();
        Queue<Position> positionQueue = new LinkedList<>();
        for (int i = 0; i < SIDE_LENGTH; i++)
            positionQueue.offer(new Position(Character.toString('a' + i) + "1"));
        rank.add(Piece.createWhiteRook(positionQueue.poll()));
        rank.add(Piece.createWhiteKnight(positionQueue.poll()));
        rank.add(Piece.createWhiteBishop(positionQueue.poll()));
        rank.add(Piece.createWhiteQueen(positionQueue.poll()));
        rank.add(Piece.createWhiteKing(positionQueue.poll()));
        rank.add(Piece.createWhiteBishop(positionQueue.poll()));
        rank.add(Piece.createWhiteKnight(positionQueue.poll()));
        rank.add(Piece.createWhiteRook(positionQueue.poll()));
        board.add(rank);
    }

    private void initBlackEdgeRank() {
        Rank rank = new Rank();
        Queue<Position> positionQueue = new LinkedList<>();
        for (int i = 0; i < SIDE_LENGTH; i++)
            positionQueue.offer(new Position(Character.toString('a' + i) + "8"));
        rank.add(Piece.createBlackRook(positionQueue.poll()));
        rank.add(Piece.createBlackKnight(positionQueue.poll()));
        rank.add(Piece.createBlackBishop(positionQueue.poll()));
        rank.add(Piece.createBlackQueen(positionQueue.poll()));
        rank.add(Piece.createBlackKing(positionQueue.poll()));
        rank.add(Piece.createBlackBishop(positionQueue.poll()));
        rank.add(Piece.createBlackKnight(positionQueue.poll()));
        rank.add(Piece.createBlackRook(positionQueue.poll()));
        board.add(rank);
    }

    private void initPawnRank(Piece.Color color) {
        Rank rank = new Rank();

        for (int i = 0; i < SIDE_LENGTH; i++)
            rank.add(color.equals(Piece.Color.WHITE) ?
                    Piece.createWhitePawn(new Position(Character.toString('a' + i) + "2")) :
                    Piece.createBlackPawn(new Position(Character.toString('a' + i) + "7")));
        board.add(rank);
    }

    private void initBlankRank(int rankNum) {
        Rank rank = new Rank();
        for (int i = 0; i < SIDE_LENGTH; i++)
            rank.add(Piece.createBlank(new Position(Character.toString('a' + i) + rankNum)));
        board.add(rank);
    }

    public Piece findPiece(String position) {
        return board.get(getRankNumFromPos(position))
                .get(getRowNumFromPos(position));
    }

    public void putPiece(String position, Piece piece) {
        Rank rank = board.get(getRankNumFromPos(position));
        verifyMovePiece(position, piece, rank);
        rank.set(getRowNumFromPos(position), piece);
    }

    private static void verifyMovePiece(String position, Piece piece, Rank rank) {
        if(rank.get(getRowNumFromPos(position)).getColor().equals(piece.getColor()))
            throw new CannotMovePosException();
    }

    public List<Piece> getRow(int rowNum) {
        List<Piece> pieces = new ArrayList<>();
        for (int rankNum = 0; rankNum < SIDE_LENGTH; rankNum++)
            pieces.add(board.get(rankNum).get(rowNum));
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
