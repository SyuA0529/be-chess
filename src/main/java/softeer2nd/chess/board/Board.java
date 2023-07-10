package softeer2nd.chess.board;

import softeer2nd.chess.exception.MissingKingException;
import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Piece.Type;
import softeer2nd.chess.pieces.Piece.Color;
import softeer2nd.chess.pieces.Position;

import java.util.*;

import static softeer2nd.utils.StringUtils.appendNewLine;

public class Board {
    public static final int SIDE_LENGTH = 8;

    private final List<Rank> ranks = new ArrayList<>();

    public void initializeEmpty() {
        ranks.clear();
        for (int rankNum = 0; rankNum < SIDE_LENGTH; rankNum++) {
            ranks.add(Rank.getBlankRank(rankNum));
        }
    }

    public void initialize() {
        ranks.clear();
        ranks.add(Rank.getEdgeRank(Color.WHITE));
        ranks.add(Rank.getPawnRank(Color.WHITE));
        for (int rankNum = 3; rankNum < SIDE_LENGTH - 1; rankNum++) {
            ranks.add(Rank.getBlankRank(rankNum));
        }
        ranks.add(Rank.getPawnRank(Color.BLACK));
        ranks.add(Rank.getEdgeRank(Color.BLACK));
    }

    public Piece findPiece(Position position) {
        return ranks.get(position.getRankNum())
                .get(position.getFileNum());
    }

    public void putPiece(Position position, Piece piece) {
        ranks.get(position.getRankNum())
                .set(position.getFileNum(), piece);
    }

    public Position getKingPositionByColor(Color color) {
        Rank kingRank = ranks.stream()
                .filter(r -> r.countSpecificPieces(color, Type.KING) > 0)
                .findFirst()
                .orElseThrow(MissingKingException::new);

        return kingRank.getKingPosition();
    }

    public List<Piece> getPiecesInFileByColor(int fileNum, Color color) {
        List<Piece> pieces = new ArrayList<>();
        for (int rankNum = 0; rankNum < SIDE_LENGTH; rankNum++) {
            Piece curPiece = ranks.get(rankNum).get(fileNum);
            if (curPiece.isColor(color)) {
                pieces.add(ranks.get(rankNum).get(fileNum));
            }
        }
        return pieces;
    }

    public List<Piece> getPiecesByColor(Color color) {
        List<Piece> pieces = new ArrayList<>();
        ranks.forEach(r -> pieces.addAll(r.getPiecesByColor(color)));
        return pieces;
    }

    public int countTotalPieces() {
        return ranks.stream()
                .mapToInt(Rank::countTotalPieces)
                .sum();
    }

    public int countPiecesByTypeAndColor(Color color, Type type) {
        return ranks.stream()
                .mapToInt(r -> r.countSpecificPieces(color, type))
                .sum();
    }

    public List<Piece> getSortedPiecesByPointAndColor(Color color) {
        List<Piece> pieces = new ArrayList<>();
        ranks.forEach(r -> pieces.addAll(r.getPiecesByColor(color)));
        pieces.sort(Comparator.reverseOrder());
        return pieces;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int rankNum = Board.SIDE_LENGTH - 1; rankNum >= 0; rankNum--) {
            sb.append(appendNewLine(ranks.get(rankNum).showRank()));
        }
        return sb.toString();
    }
}
