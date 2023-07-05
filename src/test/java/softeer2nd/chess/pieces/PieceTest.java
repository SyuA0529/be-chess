package softeer2nd.chess.pieces;

import org.junit.jupiter.api.*;
import softeer2nd.chess.pieces.Piece.Type;

import static org.assertj.core.api.Assertions.*;

public class PieceTest {
    @Test
    @DisplayName("eunm으로부터 Representation 가져오기")
    void getRepresentationFromEnumTest() {
        assertThat(Piece.Type.PAWN.getWhiteRepresentation()).isEqualTo('p');
        assertThat(Piece.Type.ROOK.getWhiteRepresentation()).isEqualTo('r');
        assertThat(Piece.Type.KNIGHT.getWhiteRepresentation()).isEqualTo('n');
        assertThat(Piece.Type.BISHOP.getWhiteRepresentation()).isEqualTo('b');
        assertThat(Piece.Type.QUEEN.getWhiteRepresentation()).isEqualTo('q');
        assertThat(Piece.Type.KING.getWhiteRepresentation()).isEqualTo('k');

        assertThat(Piece.Type.PAWN.getBlackRepresentation()).isEqualTo('P');
        assertThat(Piece.Type.ROOK.getBlackRepresentation()).isEqualTo('R');
        assertThat(Piece.Type.KNIGHT.getBlackRepresentation()).isEqualTo('N');
        assertThat(Piece.Type.BISHOP.getBlackRepresentation()).isEqualTo('B');
        assertThat(Piece.Type.QUEEN.getBlackRepresentation()).isEqualTo('Q');
        assertThat(Piece.Type.KING.getBlackRepresentation()).isEqualTo('K');
    }
    
    @Test
    @DisplayName("기물 생성")
    void createPiece() {
        verifyPiece(Piece.createWhitePawn(), Piece.createBlackPawn(), Type.PAWN);
        verifyPiece(Piece.createWhiteKnight(), Piece.createBlackKnight(), Type.KNIGHT);
        verifyPiece(Piece.createWhiteRook(), Piece.createBlackRook(), Type.ROOK);
        verifyPiece(Piece.createWhiteBishop(), Piece.createBlackBishop(), Type.BISHOP);
        verifyPiece(Piece.createWhiteQueen(), Piece.createBlackQueen(), Type.QUEEN);
        verifyPiece(Piece.createWhiteKing(), Piece.createBlackKing(), Type.KING);

        Piece blank = Piece.createBlank();
        assertThat(blank.isWhite()).isFalse();
        assertThat(blank.isBlack()).isFalse();
        assertThat(blank.getType()).isEqualTo(Type.NO_PIECE);
    }

    private void verifyPiece(final Piece whitePiece, final Piece blackPiece, final Type type) {
        assertThat(whitePiece.isWhite()).isTrue();
        assertThat(whitePiece.getType()).isEqualTo(type);

        assertThat(blackPiece.isBlack()).isTrue();
        assertThat(blackPiece.getType()).isEqualTo(type);
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
}