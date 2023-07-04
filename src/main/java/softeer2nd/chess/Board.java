package softeer2nd.chess;

import softeer2nd.chess.pieces.Pawn;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private static final int SIDE_LENGTH = 8;
    private static final String BLANK_PIECE = ".";

    private final List<Pawn> pawns = new ArrayList<>();
    private final List<Pawn> whitePawns = new ArrayList<>();
    private final List<Pawn> blackPawns = new ArrayList<>();

    public String getWhitePawnsResult() {
        StringBuilder sb = new StringBuilder();
        for (Pawn whitePawn : whitePawns)
            sb.append(whitePawn.getRepresentation());
        return sb.toString();
    }

    public String getBlackPawnsResult() {
        StringBuilder sb = new StringBuilder();
        for (Pawn blackpawn : blackPawns)
            sb.append(blackpawn.getRepresentation());
        return sb.toString();
    }

    public void initialize() {
        for (int i = 0; i < SIDE_LENGTH; i++) {
            whitePawns.add(new Pawn(Pawn.WHITE_COLOR));
            blackPawns.add(new Pawn(Pawn.BLACK_COLOR));
        }
    }

    public String print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SIDE_LENGTH; i++) {
            sb.append(getPrintLine(i));
            if(i != SIDE_LENGTH - 1) sb.append('\n');
        }
        return sb.toString();
    }

    private String getPrintLine(int lineNum) {
        if(lineNum == 1 || lineNum == 6)
            return lineNum == 1 ? getBlackPawnsResult() : getWhitePawnsResult();
        return BLANK_PIECE.repeat(SIDE_LENGTH);
    }

    public void add(Pawn pawn) {
        pawns.add(pawn);
    }

    public int size() {
        return pawns.size();
    }

    public Pawn get(int idx) {
        return pawns.get(idx);
    }
}
