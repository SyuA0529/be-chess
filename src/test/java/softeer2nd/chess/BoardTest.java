package softeer2nd.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.board.Board;
import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Piece.Color;
import softeer2nd.chess.pieces.Piece.Type;
import softeer2nd.chess.pieces.Position;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static softeer2nd.utils.StringUtils.appendNewLine;

public class BoardTest {
    private Board board;

    @BeforeEach
    public void setup() {
        board = new Board();
    }

    @Test
    @DisplayName("기물의 색에 해당하는 기물의 개수 반환")
    void countSpecificPiece() {
        board.initialize();
        Color[] colors = {Color.BLACK, Color.WHITE};
        Type[] hasOnePieceTypes = {Type.KING, Type.QUEEN};
        Type[] hasTwoPieceTypes = {Type.ROOK, Type.KNIGHT, Type.BISHOP};

        for (Color color : colors) {
            for (Type hasOnePieceType : hasOnePieceTypes)
                assertThat(board.countSpecificTypePieces(color, hasOnePieceType)).isEqualTo(1);

            for (Type hasTwoPieceType : hasTwoPieceTypes)
                assertThat(board.countSpecificTypePieces(color, hasTwoPieceType)).isEqualTo(2);
        }
    }

    @Test
    @DisplayName("주어진 위치의 기물 조회")
    void findPiece() {
        board.initialize();

        assertThat(board.findPiece("a8")).isEqualTo(Piece.createBlackRook(new Position("a8")));
        assertThat(board.findPiece("h8")).isEqualTo(Piece.createBlackRook(new Position("h8")));
        assertThat(board.findPiece("a1")).isEqualTo(Piece.createWhiteRook(new Position("a1")));
        assertThat(board.findPiece("h1")).isEqualTo(Piece.createWhiteRook(new Position("h1")));
    }

    @Test
    @DisplayName("기물을 특정 위치에 추가")
    void putPiece() {
        board.initializeEmpty();

        String position = "b5";
        Piece piece = Piece.createBlackRook(new Position("b5"));
        board.putPiece(position, piece);
        assertThat(board.findPiece(position)).isEqualTo(piece);
    }



    @Test
    @DisplayName("흰색 기물들을 점수를 기준으로 내림차순 정렬")
    void sortWhitePieceByPointDecrease() {
        //given
        board.initialize();

        //when
        List<Piece> whitePieces = board.getSortedColorPiecesByPoint(Color.WHITE);

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
        List<Piece> blackPieces = board.getSortedColorPiecesByPoint(Color.BLACK);

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