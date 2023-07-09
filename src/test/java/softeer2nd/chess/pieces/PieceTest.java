package softeer2nd.chess.pieces;

import org.junit.jupiter.api.*;


import softeer2nd.chess.pieces.Piece.Type;
import softeer2nd.chess.pieces.Piece.Color;

import static org.assertj.core.api.Assertions.*;

public class PieceTest {
    final Type[] types = {Type.PAWN, Type.KNIGHT, Type.ROOK, Type.BISHOP, Type.QUEEN, Type.KING};
    final Color[] colors = {Color.WHITE, Color.BLACK};

    @Nested
    @DisplayName("isColor method")
    class IsColor {
        @Nested
        @DisplayName("주어진 색상과 기물의 색상이 동일하다면")
        class GivenColorIsEqualToPieceColor {
            @Test
            @DisplayName("true를 반환한다")
            void returnTrue() {
                for (Color color : colors) {
                    for (Type type : types) {
                        Piece piece = PieceFactory.createNotBlank(color, type, new Position("a1"));
                        assertThat(piece.isColor(color)).isTrue();
                    }
                }
            }
        }

        @Nested
        @DisplayName("주어진 색상과 기물의 색상이 동일하지 않다면")
        class GivenColorIsNotEqualToPieceColor {
            @Test
            @DisplayName("false를 반환한다")
            void returnFalse() {
                for (Type type : types) {
                    Piece piece = PieceFactory.createNotBlank(Color.WHITE, type, new Position("a1"));
                    assertThat(piece.isColor(Color.BLACK)).isFalse();
                }

                for (Type type : types) {
                    Piece piece = PieceFactory.createNotBlank(Color.BLACK, type, new Position("a1"));
                    assertThat(piece.isColor(Color.WHITE)).isFalse();
                }
            }
        }
    }

    @Nested
    @DisplayName("getRepresentation method")
    class GetRepresentation {
        @Nested
        @DisplayName("기물이 흰색이라면")
        class IsPieceColorWhite {
            @Test
            @DisplayName("기물의 흰색 표현을 반환한다")
            void returnWhiteRepresentation() {
                for (Type type : types) {
                    char representation = PieceFactory.createNotBlank(Color.WHITE, type, new Position("a1"))
                            .getRepresentation();
                    assertThat(representation).isEqualTo(type.getWhiteRepresentation());
                }
            }
        }

        @Nested
        @DisplayName("기물이 검은색이라면")
        class IsPieceColorBlack {
            @Test
            @DisplayName("기물의 검은색 표현을 반환한다")
            void returnBlackRepresentation() {
                for (Type type : types) {
                    char representation = PieceFactory.createNotBlank(Color.BLACK, type, new Position("a1"))
                            .getRepresentation();
                    assertThat(representation).isEqualTo(type.getBlackRepresentation());
                }
            }
        }
    }

    @Nested
    @DisplayName("isType method")
    class IsType {
        @Nested
        @DisplayName("주어진 유형이 기물의 유형과 동일하다면")
        class GivenTypeIsEqualToPieceType {
            @Test
            @DisplayName("true를 반환한다")
            void returnTrue() {
                for (Type type : types) {
                    Piece piece = PieceFactory.createNotBlank(Color.WHITE, type, new Position("a1"));
                    assertThat(piece.isType(type)).isTrue();
                }
            }
        }

        @Nested
        @DisplayName("주어진 유형이 기물의 유형과 동일하지 않다면")
        class GivenTypeIsNotEqualToPieceType {
            @Test
            @DisplayName("false를 반환한다")
            void returnFalse() {
                for (Type curType : types) {
                    Piece piece = PieceFactory.createNotBlank(Color.WHITE, curType, new Position("a1"));
                    for (Type checkType : types) {
                        if (curType.equals(checkType)) {
                            continue;
                        }
                        assertThat(piece.isType(checkType)).isFalse();
                    }
                }
            }
        }
    }

    @Nested
    @DisplayName("changePosition method")
    class ChangePosition {
        @Test
        @DisplayName("기물의 위치를 주어진 위치로 변경한다")
        void changePiecePositionToGivenPosition() {
            for (Color color : colors) {
                for (Type type : types) {
                    Piece piece = PieceFactory.createNotBlank(color, type, new Position("a1"));
                    piece.changePosition(new Position("a2"));
                    assertThat(piece.getPosition().getFileNum()).isEqualTo(0);
                    assertThat(piece.getPosition().getRankNum()).isEqualTo(1);
                }
            }
        }
    }

    @Nested
    @DisplayName("getDefaultPoint method")
    class GetDefaultPoint {
        @Test
        @DisplayName("기물의 유형에 맞는 기본 점수를 반환한다")
        void returnPieceTypeDefaultPoint() {
            for (Color color : colors) {
                for (Type type : types) {
                    Piece piece = PieceFactory.createNotBlank(color, type, new Position("a1"));
                    assertThat(piece.getDefaultPoint()).isEqualTo(type.getDefaultPoint());
                }
            }
        }
    }
}
