package softeer2nd.chess.pieces;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static softeer2nd.chess.pieces.Piece.*;
import static softeer2nd.chess.pieces.Piece.Type;
import static softeer2nd.chess.pieces.PieceFactory.*;

public class PieceTest {
    Type[] types = {Type.PAWN, Type.KNIGHT, Type.ROOK, Type.BISHOP, Type.QUEEN, Type.KING};

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
        Position position = new Position("a1");
        for (Type type : types)
            verifyPiece(createNotBlank(Color.WHITE, type, position),
                        createNotBlank(Color.BLACK, type, position),
                        type);


        Piece blank = createBlank(position);
        assertThat(blank.isBlank()).isTrue();
        assertThat(blank.getType()).isEqualTo(Type.NO_PIECE);
    }

    private void verifyPiece(final Piece whitePiece, final Piece blackPiece, final Type type) {
        assertThat(whitePiece.isColor(Color.WHITE)).isTrue();
        assertThat(whitePiece.getType()).isEqualTo(type);

        assertThat(blackPiece.isColor(Color.BLACK)).isTrue();
        assertThat(blackPiece.getType()).isEqualTo(type);
    }

    @Test
    @DisplayName("기물 색상 검증")
    void testVerifyPieceColor() {
        Position position = new Position("a1");

        List<Piece> whitePieces = new ArrayList<>();
        List<Piece> blackPieces = new ArrayList<>();
        for (Type type : types) {
            whitePieces.add(createNotBlank(Color.WHITE, type, position));
            blackPieces.add(createNotBlank(Color.BLACK, type, position));
        }

        for (int i = 0; i < whitePieces.size(); i++) {
            assertThat(whitePieces.get(i).isColor(Color.WHITE)).isTrue();
            assertThat(whitePieces.get(i).isColor(Color.BLACK)).isFalse();
            assertThat(blackPieces.get(i).isColor(Color.WHITE)).isFalse();
            assertThat(blackPieces.get(i).isColor(Color.BLACK)).isTrue();
        }
    }

    @Test
    @DisplayName("기물 이름 검증")
    void testVerifyPieceRepresentation() {
        Position position = new Position("a1");
        for (Type type : types) {
            assertThat(PieceFactory.createNotBlank(Color.WHITE, type, position).getRepresentation())
                    .isEqualTo(type.getWhiteRepresentation());
            assertThat(PieceFactory.createNotBlank(Color.BLACK, type, position).getRepresentation())
                    .isEqualTo(type.getBlackRepresentation());
        }
    }
}