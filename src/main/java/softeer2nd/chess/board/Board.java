package softeer2nd.chess.board;

import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Piece.Type;
import softeer2nd.chess.pieces.Piece.Color;
import softeer2nd.chess.pieces.Position;

import java.util.*;

import static softeer2nd.utils.StringUtils.appendNewLine;


public class Board {
    public static final int SIDE_LENGTH = 8;

    private final List<Rank> board = new ArrayList<>();

    public void initializeEmpty() {
        board.clear();
        for (int rankNum = 0; rankNum < SIDE_LENGTH; rankNum++) {
            board.add(Rank.getBlankRank(rankNum));
        }
    }

    public void initialize() {
        board.clear();
        board.add(Rank.getEdgeRank(Color.WHITE));
        board.add(Rank.getPawnRank(Color.WHITE));
        for (int rankNum = 3; rankNum < SIDE_LENGTH - 1; rankNum++) {
            board.add(Rank.getBlankRank(rankNum));
        }
        board.add(Rank.getPawnRank(Color.BLACK));
        board.add(Rank.getEdgeRank(Color.BLACK));
    }

    public Piece findPiece(Position position) {
        return board.get(position.getRankNum())
                .get(position.getFileNum());
    }

    public void putPiece(Position position, Piece piece) {
        board.get(position.getRankNum())
                .set(position.getFileNum(), piece);
    }

    public List<Piece> getPiecesInFileByColor(int fileNum, Color color) {
        List<Piece> pieces = new ArrayList<>();
        for (int rankNum = 0; rankNum < SIDE_LENGTH; rankNum++){
            Piece curPiece = board.get(rankNum).get(fileNum);
            if(curPiece.isColor(color)){
                pieces.add(board.get(rankNum).get(fileNum));
            }
        }
        return pieces;
    }

    public int countTotalPieces() {
        return board.stream()
                .mapToInt(Rank::countTotalPieces)
                .sum();
    }

    public int countPiecesByTypeAndColor(Color color, Type type) {
        return board.stream()
                .mapToInt(r -> r.countSpecificPieces(color, type))
                .sum();
    }

    public List<Piece> getSortedPiecesByPointAndColor(Color color) {
        List<Piece> pieces = new ArrayList<>();
        board.forEach(r -> pieces.addAll(r.getPiecesByColor(color)));
        pieces.sort(Comparator.reverseOrder());
        return pieces;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int rankNum = Board.SIDE_LENGTH - 1; rankNum >= 0; rankNum--) {
            sb.append(appendNewLine(board.get(rankNum).showRank()));
        }
        return sb.toString();
    }
}
