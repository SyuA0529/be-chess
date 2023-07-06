package softeer2nd.chess.pieces;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;
import static softeer2nd.chess.pieces.Piece.*;

public class PieceTest {
    @Test
    @DisplayName("eunm으로부터 Representation 가져오기")
    void getRepresentationFromEnumTest() {
        assertThat(Type.PAWN.getWhiteRepresentation()).isEqualTo('p');
        assertThat(Type.ROOK.getWhiteRepresentation()).isEqualTo('r');
        assertThat(Type.KNIGHT.getWhiteRepresentation()).isEqualTo('n');
        assertThat(Type.BISHOP.getWhiteRepresentation()).isEqualTo('b');
        assertThat(Type.QUEEN.getWhiteRepresentation()).isEqualTo('q');
        assertThat(Type.KING.getWhiteRepresentation()).isEqualTo('k');

        assertThat(Type.PAWN.getBlackRepresentation()).isEqualTo('P');
        assertThat(Type.ROOK.getBlackRepresentation()).isEqualTo('R');
        assertThat(Type.KNIGHT.getBlackRepresentation()).isEqualTo('N');
        assertThat(Type.BISHOP.getBlackRepresentation()).isEqualTo('B');
        assertThat(Type.QUEEN.getBlackRepresentation()).isEqualTo('Q');
        assertThat(Type.KING.getBlackRepresentation()).isEqualTo('K');
    }

    @Test
    @DisplayName("기물 생성")
    void createPiece() {
        verifyPiece(createWhitePawn(), createBlackPawn(), Type.PAWN);
        verifyPiece(createWhiteKnight(), createBlackKnight(), Type.KNIGHT);
        verifyPiece(createWhiteRook(), createBlackRook(), Type.ROOK);
        verifyPiece(createWhiteBishop(), createBlackBishop(), Type.BISHOP);
        verifyPiece(createWhiteQueen(), createBlackQueen(), Type.QUEEN);
        verifyPiece(createWhiteKing(), createBlackKing(), Type.KING);

        Piece blank = createBlank();
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
                createWhitePawn(), createWhiteKnight(), createWhiteRook(),
                createWhiteBishop(), createWhiteQueen(), createWhiteKing()
        };

        Piece[] blackPieces = {
                createBlackPawn(), createBlackKnight(), createBlackRook(),
                createBlackBishop(), createBlackQueen(), createBlackKing()
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

    @Test
    @DisplayName("기물 이름 검증")
    void testVerifyPieceRepresentation() {
        assertThat(Piece.createWhiteKing().getRepresentation()).isEqualTo(Type.KING.getWhiteRepresentation());
        assertThat(Piece.createWhiteQueen().getRepresentation()).isEqualTo(Type.QUEEN.getWhiteRepresentation());
        assertThat(Piece.createWhiteBishop().getRepresentation()).isEqualTo(Type.BISHOP.getWhiteRepresentation());
        assertThat(Piece.createWhiteKnight().getRepresentation()).isEqualTo(Type.KNIGHT.getWhiteRepresentation());
        assertThat(Piece.createWhiteRook().getRepresentation()).isEqualTo(Type.ROOK.getWhiteRepresentation());
        assertThat(Piece.createWhitePawn().getRepresentation()).isEqualTo(Type.PAWN.getWhiteRepresentation());


        assertThat(Piece.createBlackKing().getRepresentation()).isEqualTo(Type.KING.getBlackRepresentation());
        assertThat(Piece.createBlackQueen().getRepresentation()).isEqualTo(Type.QUEEN.getBlackRepresentation());
        assertThat(Piece.createBlackBishop().getRepresentation()).isEqualTo(Type.BISHOP.getBlackRepresentation());
        assertThat(Piece.createBlackKnight().getRepresentation()).isEqualTo(Type.KNIGHT.getBlackRepresentation());
        assertThat(Piece.createBlackRook().getRepresentation()).isEqualTo(Type.ROOK.getBlackRepresentation());
        assertThat(Piece.createBlackPawn().getRepresentation()).isEqualTo(Type.PAWN.getBlackRepresentation());
    }
}