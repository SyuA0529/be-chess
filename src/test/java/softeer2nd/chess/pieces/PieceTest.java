package softeer2nd.chess.pieces;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PieceTest {
    @Test
    public void create_piece() {
        verifyPiece(Piece.createWhitePawn(), Piece.WHITE_COLOR, Piece.WHITE_PAWN_REPRESENTATION);
        verifyPiece(Piece.createBlackPawn(), Piece.BLACK_COLOR, Piece.BLACK_PAWN_REPRESENTATION);

        verifyPiece(Piece.createWhiteKnight(), Piece.WHITE_COLOR, Piece.WHITE_KNIGHT_REPRESENTATION);
        verifyPiece(Piece.createBlackKnight(), Piece.BLACK_COLOR, Piece.BLACK_KNIGHT_REPRESENTATION);

        verifyPiece(Piece.createWhiteRook(), Piece.WHITE_COLOR, Piece.WHITE_ROOK_REPRESENTATION);
        verifyPiece(Piece.createBlackRook(), Piece.BLACK_COLOR, Piece.BLACK_ROOK_REPRESENTATION);

        verifyPiece(Piece.createWhiteBishop(), Piece.WHITE_COLOR, Piece.WHITE_BISHOP_REPRESENTATION);
        verifyPiece(Piece.createBlackBishop(), Piece.BLACK_COLOR, Piece.BLACK_BISHOP_REPRESENTATION);

        verifyPiece(Piece.createWhiteQueen(), Piece.WHITE_COLOR, Piece.WHITE_QUEEN_REPRESENTATION);
        verifyPiece(Piece.createBlackQueen(), Piece.BLACK_COLOR, Piece.BLACK_QUEEN_REPRESENTATION);

        verifyPiece(Piece.createWhiteKing(), Piece.WHITE_COLOR, Piece.WHITE_KING_REPRESENTATION);
        verifyPiece(Piece.createBlackKing(), Piece.BLACK_COLOR, Piece.BLACK_KING_REPRESENTATION);
    }

    @Test
    @DisplayName("기물 색상 검증")
    void testVerifyPieceColor() {
        Piece[] whitePieces = {
                Piece.createWhitePawn(), Piece.createWhiteKnight(), Piece.createWhiteRook(),
                Piece.createWhiteBishop(), Piece.createWhiteQueen(), Piece.createWhiteKing()
        };

        Piece[] blackPieces = {
                Piece.createBlackPawn(), Piece.createBlackKnight(), Piece.createBlackRook(),
                Piece.createBlackBishop(), Piece.createBlackQueen(), Piece.createBlackKing()
        };

        for (Piece whitePiece : whitePieces) {
            assertThat(whitePiece.isWhite()).isTrue();
            assertThat(whitePiece.isBlack()).isFalse();
        }


        for (Piece blackPiece : blackPieces) {
            assertThat(blackPiece.isWhite()).isFalse();
            assertThat(blackPiece.isBlack()).isTrue();
        }
    }

    private void verifyPiece(final Piece piece, final String color, final char representation) {
        assertEquals(color, piece.getColor());
        assertEquals(representation, piece.getRepresentation());
    }
}