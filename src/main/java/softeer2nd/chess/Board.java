package softeer2nd.chess;

import softeer2nd.chess.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

import static softeer2nd.utils.StringUtils.*;

public class Board {
    private static final int SIDE_LENGTH = 8;

    private final List<Piece> whitePieces = new ArrayList<>();
    private final List<Piece> blackPieces = new ArrayList<>();

    public void initialize() {
        initWhitePiece();
        initBlackPieces();
    }

    private void initWhitePiece() {
        whitePieces.clear();
        for (int i = 0; i < SIDE_LENGTH; i++) whitePieces.add(Piece.createWhitePawn());
        whitePieces.add(Piece.createWhiteRook());
        whitePieces.add(Piece.createWhiteKnight());
        whitePieces.add(Piece.createWhiteBishop());
        whitePieces.add(Piece.createWhithQueen());
        whitePieces.add(Piece.createWhithKing());
        whitePieces.add(Piece.createWhiteBishop());
        whitePieces.add(Piece.createWhiteKnight());
        whitePieces.add(Piece.createWhiteRook());
    }

    private void initBlackPieces() {
        blackPieces.clear();
        for (int i = 0; i < SIDE_LENGTH; i++) blackPieces.add(Piece.createBlackPawn());
        blackPieces.add(Piece.createBlackRook());
        blackPieces.add(Piece.createBlackKnight());
        blackPieces.add(Piece.createBlackBishop());
        blackPieces.add(Piece.createBlackQueen());
        blackPieces.add(Piece.createBlackKing());
        blackPieces.add(Piece.createBlackBishop());
        blackPieces.add(Piece.createBlackKnight());
        blackPieces.add(Piece.createBlackRook());
    }

    public String showBoard() {
        String blankRank = "........";
        StringBuilder sb = new StringBuilder();
        sb.append(getPrintLine(blackPieces.subList(SIDE_LENGTH, blackPieces.size())));
        sb.append(getPrintLine(blackPieces.subList(0, SIDE_LENGTH)));

        sb.append(appendNewLine(blankRank).repeat(4));

        sb.append(getPrintLine(whitePieces.subList(0, SIDE_LENGTH)));
        sb.append(getPrintLine(whitePieces.subList(SIDE_LENGTH, whitePieces.size())));
        return sb.toString();
    }

    private static String getPrintLine(List<Piece> pieces) {
        StringBuilder sb = new StringBuilder();
        pieces.forEach(p -> sb.append(p.getRepresentation()));
        return appendNewLine(sb.toString());
    }

    public int pieceCount() {
        return whitePieces.size() + blackPieces.size();
    }
}
