package softeer2nd.chess.board;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.PieceFactory;
import softeer2nd.chess.pieces.Position;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class RankTest {
    @Nested
    @DisplayName("getEdgeRank method")
    class GetEdgeRank {
        @Test
        @DisplayName("가장자리 RANK(RNBQKBNR)을 반환한다")
        void returnEdgeRank() {
            //given
            //when
            Rank whiteColorEdge = Rank.getEdgeRank(Piece.Color.WHITE);
            Rank blackColorEdge = Rank.getEdgeRank(Piece.Color.BLACK);

            //then
            Piece.Type[] types = {
                    Piece.Type.ROOK, Piece.Type.KNIGHT, Piece.Type.BISHOP, Piece.Type.QUEEN,
                    Piece.Type.KING, Piece.Type.BISHOP, Piece.Type.KNIGHT, Piece.Type.ROOK
            };
            for (int fileNum = 0; fileNum < Board.SIDE_LENGTH; fileNum++) {
                assertThat(whiteColorEdge.get(fileNum).isType(types[fileNum])).isTrue();
                assertThat(blackColorEdge.get(fileNum).isType(types[fileNum])).isTrue();
            }
        }
    }

    @Nested
    @DisplayName("getPawnRank method")
    class GetPawnRank {
        @Test
        @DisplayName("폰으로만 이루어진 RANK를 반환한다")
        void returnPawnRank() {
            //given
            //when
            Rank whiteColorPawnRank = Rank.getPawnRank(Piece.Color.WHITE);
            Rank blackColorPawnRank = Rank.getPawnRank(Piece.Color.BLACK);

            //then
            for (int fileNum = 0; fileNum < Board.SIDE_LENGTH; fileNum++) {
                assertThat(whiteColorPawnRank.get(fileNum).isType(Piece.Type.PAWN)).isTrue();
                assertThat(blackColorPawnRank.get(fileNum).isType(Piece.Type.PAWN)).isTrue();
            }
        }
    }

    @Nested
    @DisplayName("getBlankRank method")
    class GetBlankRank {
        @Test
        @DisplayName("빈 기물로만 이루어진 RANK를 반환한다")
        void returnBlankRank() {
            //given
            //when
            Rank blankRank = Rank.getBlankRank(0);

            //then
            for (int fileNum = 0; fileNum < Board.SIDE_LENGTH; fileNum++) {
                assertThat(blankRank.get(fileNum).isBlank()).isTrue();
            }
        }
    }

    @Nested
    @DisplayName("countTotalPieces method")
    class CountTotalPieces {
        @Test
        @DisplayName("빈 기물을 제외한 RANK에 존재하는 기물의 개수를 반환한다")
        void returnPieceNumberExcludeBlank() {
            //given
            Rank whiteEdgeRank = Rank.getEdgeRank(Piece.Color.WHITE);
            Rank blackEdgeRank = Rank.getEdgeRank(Piece.Color.BLACK);

            whiteEdgeRank.set(0, PieceFactory.createBlank(new Position("a1")));
            blackEdgeRank.set(0, PieceFactory.createBlank(new Position("a1")));

            //when
            //then
            assertThat(whiteEdgeRank.countTotalPieces()).isEqualTo(7);
            assertThat(blackEdgeRank.countTotalPieces()).isEqualTo(7);
        }
    }

    @Nested
    @DisplayName("countSpecificPieces method")
    class CountSpecificPieces {
        @Test
        @DisplayName("주어진 색상과 유형에 맞는 기물의 개수를 반환한다")
        void returnSpecificPiecesNumber() {
            Piece.Color[] colors = {Piece.Color.BLACK, Piece.Color.WHITE};
            Piece.Type[] hasOnePieceTypes = {Piece.Type.KING, Piece.Type.QUEEN};
            Piece.Type[] hasTwoPieceTypes = {Piece.Type.ROOK, Piece.Type.KNIGHT, Piece.Type.BISHOP};

            for (Piece.Color color : colors) {
                //given
                Rank edgeRank = Rank.getEdgeRank(color);

                //when
                //then
                for (Piece.Type hasOnePieceType : hasOnePieceTypes) {
                    assertThat(edgeRank.countSpecificPieces(color, hasOnePieceType)).isEqualTo(1);
                }
                for (Piece.Type hasTwoPieceType : hasTwoPieceTypes) {
                    assertThat(edgeRank.countSpecificPieces(color, hasTwoPieceType)).isEqualTo(2);
                }
            }
        }
    }

    @Nested
    @DisplayName("getPiecesByColor method")
    class GetPiecesByColor {
        @Test
        @DisplayName("주어진 색상의 기물들을 반환한다")
        void returnGivenColorPieces() {
            //given
            Rank edgeRank = Rank.getEdgeRank(Piece.Color.WHITE);
            edgeRank.set(1, PieceFactory.createBlank(new Position("a2")));
            edgeRank.set(1, PieceFactory.createNotBlank(Piece.Color.BLACK, Piece.Type.ROOK, new Position("a3")));

            //when
            List<Piece> piecesByColor = edgeRank.getPiecesByColor(Piece.Color.WHITE);

            //then
            for (Piece piece : piecesByColor) {
                assertThat(piece.isColor(Piece.Color.WHITE)).isTrue();
            }
        }
    }

    @Nested
    @DisplayName("showRank method")
    class ShowRank {
        @Test
        @DisplayName("현재 RANk를 문자열로 반환한다")
        void returnRankString() {
            //given
            Rank whiteEdgeRank = Rank.getEdgeRank(Piece.Color.WHITE);
            Rank blackEdgeRank = Rank.getEdgeRank(Piece.Color.BLACK);
            Rank whitePawnRank = Rank.getPawnRank(Piece.Color.WHITE);
            Rank blackPawnRank = Rank.getPawnRank(Piece.Color.BLACK);
            Rank blankRank = Rank.getBlankRank(0);

            //when
            //then
            assertThat(whiteEdgeRank.showRank()).isEqualTo("rnbqkbnr");
            assertThat(blackEdgeRank.showRank()).isEqualTo("RNBQKBNR");
            assertThat(whitePawnRank.showRank()).isEqualTo("pppppppp");
            assertThat(blackPawnRank.showRank()).isEqualTo("PPPPPPPP");
            assertThat(blankRank.showRank()).isEqualTo("........");
        }
    }
}
