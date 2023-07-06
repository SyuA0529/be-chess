package softeer2nd.chess.pieces;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;
import static softeer2nd.chess.pieces.Piece.Type;

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
        verifyPiece(Piece.createWhitePawn(new Position("a1")), Piece.createBlackPawn(new Position("a1")), Type.PAWN);
        verifyPiece(Piece.createWhiteKnight(new Position("a1")), Piece.createBlackKnight(new Position("a1")), Type.KNIGHT);
        verifyPiece(Piece.createWhiteRook(new Position("a1")), Piece.createBlackRook(new Position("a1")), Type.ROOK);
        verifyPiece(Piece.createWhiteBishop(new Position("a1")), Piece.createBlackBishop(new Position("a1")), Type.BISHOP);
        verifyPiece(Piece.createWhiteQueen(new Position("a1")), Piece.createBlackQueen(new Position("a1")), Type.QUEEN);
        verifyPiece(Piece.createWhiteKing(new Position("a1")), Piece.createBlackKing(new Position("a1")), Type.KING);

        Piece blank = Piece.createBlank(new Position("a1"));
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
                Piece.createWhitePawn(new Position("a1")), Piece.createWhiteKnight(new Position("a1")),
                Piece.createWhiteRook(new Position("a1")), Piece.createWhiteBishop(new Position("a1")),
                Piece.createWhiteQueen(new Position("a1")), Piece.createWhiteKing(new Position("a1"))
        };

        Piece[] blackPieces = {
                Piece.createBlackPawn(new Position("a1")), Piece.createBlackKnight(new Position("a1")),
                Piece.createBlackRook(new Position("a1")), Piece.createBlackBishop(new Position("a1")),
                Piece.createBlackQueen(new Position("a1")), Piece.createBlackKing(new Position("a1"))
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
        assertThat(Piece.createWhiteKing(new Position("a1")).getRepresentation()).isEqualTo(Type.KING.getWhiteRepresentation());
        assertThat(Piece.createWhiteQueen(new Position("a1")).getRepresentation()).isEqualTo(Type.QUEEN.getWhiteRepresentation());
        assertThat(Piece.createWhiteBishop(new Position("a1")).getRepresentation()).isEqualTo(Type.BISHOP.getWhiteRepresentation());
        assertThat(Piece.createWhiteKnight(new Position("a1")).getRepresentation()).isEqualTo(Type.KNIGHT.getWhiteRepresentation());
        assertThat(Piece.createWhiteRook(new Position("a1")).getRepresentation()).isEqualTo(Type.ROOK.getWhiteRepresentation());
        assertThat(Piece.createWhitePawn(new Position("a1")).getRepresentation()).isEqualTo(Type.PAWN.getWhiteRepresentation());


        assertThat(Piece.createBlackKing(new Position("a1")).getRepresentation()).isEqualTo(Type.KING.getBlackRepresentation());
        assertThat(Piece.createBlackQueen(new Position("a1")).getRepresentation()).isEqualTo(Type.QUEEN.getBlackRepresentation());
        assertThat(Piece.createBlackBishop(new Position("a1")).getRepresentation()).isEqualTo(Type.BISHOP.getBlackRepresentation());
        assertThat(Piece.createBlackKnight(new Position("a1")).getRepresentation()).isEqualTo(Type.KNIGHT.getBlackRepresentation());
        assertThat(Piece.createBlackRook(new Position("a1")).getRepresentation()).isEqualTo(Type.ROOK.getBlackRepresentation());
        assertThat(Piece.createBlackPawn(new Position("a1")).getRepresentation()).isEqualTo(Type.PAWN.getBlackRepresentation());
    }
}