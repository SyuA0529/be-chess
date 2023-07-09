package softeer2nd.chess.board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Piece.Color;
import softeer2nd.chess.pieces.Piece.Type;
import softeer2nd.chess.pieces.Position;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static softeer2nd.chess.pieces.PieceFactory.*;
import static softeer2nd.utils.StringUtils.*;

public class BoardTest {
    private Board board;

    @BeforeEach
    public void setup() {
        board = new Board();
    }


    @Nested
    @DisplayName("initializeEmpty method")
    class InitializeEmpty {
        @Test
        @DisplayName("보드판을 전부 공백으로 초기화")
        void initializeBoardEmpty() {
            //given
            board.initializeEmpty();

            //when
            //then
            assertThat(board.countPiecesByTypeAndColor(Color.NOCOLOR, Type.NO_PIECE))
                    .isEqualTo(64);
        }
    }

    @Nested
    @DisplayName("initialize & toString method")
    class Initialize {
        @Test
        @DisplayName("게임 규칙에 맞는 보드판 초기화")
        void initializeBoard() {
            //given
            board.initialize();
            String blankRank = appendNewLine("........");

            //when
            //then
            assertEquals(
                    appendNewLine("RNBQKBNR") +
                            appendNewLine("PPPPPPPP") +
                            blankRank + blankRank + blankRank + blankRank +
                            appendNewLine("pppppppp") +
                            appendNewLine("rnbqkbnr"),
                    board.toString());
        }
    }

    @Nested
    @DisplayName("findPiece method")
    class FindPiece {
        @Test
        @DisplayName("위치에 맞는 기물 반환")
        void returnMatchPositionPiece() {
            //given
            board.initialize();

            //when
            Piece whiteRook = board.findPiece(new Position("a1"));
            Piece blackRook = board.findPiece(new Position("a8"));

            //then
            verifyPiece(whiteRook, Color.WHITE, Type.ROOK, new Position("a1"));
            verifyPiece(blackRook, Color.BLACK, Type.ROOK, new Position("a8"));
        }
    }


    @Nested
    @DisplayName("putPiece method")
    class PutPiece {
        @Test
        @DisplayName("해당 위치에 기물 할당")
        void putPieceToPosition() {
            //given
            board.initializeEmpty();

            //when
            board.putPiece(new Position("c3"),
                    createNotBlank(Color.WHITE, Type.QUEEN, new Position("c3")));

            //then
            verifyPiece(board.findPiece(new Position("c3")), Color.WHITE, Type.QUEEN, new Position("c3"));
        }
    }

    @Nested
    @DisplayName("getPiecesInFileByColor method")
    class GetPiecesInFileByColor {
        @Test
        @DisplayName("주어진 FILE에 존재하는 주어진 색상의 기물 반환")
        void returnGivenColorPieceFromGivenFile() {
            //given
            board.initialize();

            //when
            List<Piece> pieces = board.getPiecesInFileByColor(0, Color.WHITE);

            //then
            List<Piece> expectPieces = List.of(createNotBlank(Color.WHITE, Type.ROOK, new Position("a1")),
                    createNotBlank(Color.WHITE, Type.PAWN, new Position("a2")));
            for (Piece piece : pieces) {
                assertThat(piece).isIn(expectPieces);
            }
        }
    }

    @Nested
    @DisplayName("countTotalPieces method")
    class CountTotalPieces {
        @Test
        @DisplayName("공백을 제외한 전체 기물 개수 반환")
        void returnTotalPieceNumExcludeBlank() {
            //given
            board.initialize();

            //when
            //then
            assertThat(board.countTotalPieces()).isEqualTo(32);
        }
    }

    @Nested
    @DisplayName("countSpecificTypePieces method")
    class CountSpecificTypePieces {
        @Test
        @DisplayName("특정 색상과 유형에 해당하는 기물 개수 반환")
        void returnSpecificColorAndTypePiecesNum() {
            //given
            board.initialize();
            Color[] colors = {Color.BLACK, Color.WHITE};
            Type[] hasOnePieceTypes = {Type.KING, Type.QUEEN};
            Type[] hasTwoPieceTypes = {Type.ROOK, Type.KNIGHT, Type.BISHOP};

            //when
            //then
            for (Color color : colors) {
                for (Type hasOnePieceType : hasOnePieceTypes) {
                    assertThat(board.countPiecesByTypeAndColor(color, hasOnePieceType)).isEqualTo(1);
                }

                for (Type hasTwoPieceType : hasTwoPieceTypes) {
                    assertThat(board.countPiecesByTypeAndColor(color, hasTwoPieceType)).isEqualTo(2);
                }
            }
        }
    }

    @Nested
    @DisplayName("getSortedColorPiecesByPoint method")
    class GetSortedColorPiecesByPoint {
        @Test
        @DisplayName("흰색 기물들을 점수를 기준으로 내림차순 정렬")
        void sortWhitePieceByPointDecrease() {
            //given
            board.initialize();

            //when
            List<Piece> whitePieces = board.getSortedPiecesByPointAndColor(Color.WHITE);

            //then
            assertThat(whitePieces.get(0).getType()).isEqualTo(Type.QUEEN);
            for (int i = 0; i < 2; i++)
                assertThat(whitePieces.get(1 + i).getType()).isEqualTo(Type.ROOK);
            for (int i = 0; i < 2; i++)
                assertThat(whitePieces.get(3 + i).getType()).isEqualTo(Type.BISHOP);
            for (int i = 0; i < 2; i++)
                assertThat(whitePieces.get(5 + i).getType()).isEqualTo(Type.KNIGHT);
            for (int i = 0; i < 8; i++)
                assertThat(whitePieces.get(7 + i).getType()).isEqualTo(Type.PAWN);
            assertThat(whitePieces.get(whitePieces.size() - 1).getType()).isEqualTo(Type.KING);
        }

        @Test
        @DisplayName("검은색 기물들을 점수를 기준으로 내림차순 정렬")
        void sortBlackPieceByPointDecrease() {
            //given
            board.initialize();

            //when
            List<Piece> blackPieces = board.getSortedPiecesByPointAndColor(Color.BLACK);

            //then
            assertThat(blackPieces.get(0).getType()).isEqualTo(Type.QUEEN);
            for (int i = 0; i < 2; i++)
                assertThat(blackPieces.get(1 + i).getType()).isEqualTo(Type.ROOK);
            for (int i = 0; i < 2; i++)
                assertThat(blackPieces.get(3 + i).getType()).isEqualTo(Type.BISHOP);
            for (int i = 0; i < 2; i++)
                assertThat(blackPieces.get(5 + i).getType()).isEqualTo(Type.KNIGHT);
            for (int i = 0; i < 8; i++)
                assertThat(blackPieces.get(7 + i).getType()).isEqualTo(Type.PAWN);
            assertThat(blackPieces.get(blackPieces.size() - 1).getType()).isEqualTo(Type.KING);
        }
    }

    private static void verifyPiece(Piece piece, Color color, Type type, Position position) {
        assertThat(piece).isEqualTo(createNotBlank(color, type, position));
    }
}
