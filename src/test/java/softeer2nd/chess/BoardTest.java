package softeer2nd.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Piece.Color;
import softeer2nd.chess.pieces.Piece.Type;

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
    public void create() {
        board.initialize();
        assertEquals(32, board.countTotalPieces());
        String blankRank = appendNewLine("........");
        assertEquals(
                appendNewLine("RNBQKBNR") +
                        appendNewLine("PPPPPPPP") +
                        blankRank + blankRank + blankRank + blankRank +
                        appendNewLine("pppppppp") +
                        appendNewLine("rnbqkbnr"),
                board.showBoard());
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

        assertThat(board.findPiece("a8")).isEqualTo(Piece.createBlackRook());
        assertThat(board.findPiece("h8")).isEqualTo(Piece.createBlackRook());
        assertThat(board.findPiece("a1")).isEqualTo(Piece.createWhiteRook());
        assertThat(board.findPiece("h1")).isEqualTo(Piece.createWhiteRook());
    }

    @Test
    @DisplayName("기물 이동")
    void move() {
        board.initializeEmpty();

        String position = "b5";
        Piece piece = Piece.createBlackRook();
        board.move(position, piece);
        System.out.println(board.showBoard());
        assertThat(board.findPiece(position)).isEqualTo(piece);
    }

    @Test
    @DisplayName("기물 점수 계산")
    void calculatePoint() {
        board.initializeEmpty();

        addPiece("b6", Piece.createBlackPawn());
        addPiece("e6", Piece.createBlackQueen());
        addPiece("b8", Piece.createBlackKing());
        addPiece("c8", Piece.createBlackRook());

        addPiece("f2", Piece.createWhitePawn());
        addPiece("g2", Piece.createWhitePawn());
        addPiece("e1", Piece.createWhiteRook());
        addPiece("f1", Piece.createWhiteKing());

        assertEquals(15.0, board.calculatePoint(Color.BLACK), 0.01);
        assertEquals(7.0, board.calculatePoint(Color.WHITE), 0.01);

        System.out.println(board.showBoard());
    }

    @Test
    @DisplayName("흰색 기물들을 점수를 기준으로 내림차순 정렬")
    void sortWhitePieceByPointDecrease() {
        //given
        board.initialize();

        //when
        List<Piece> whitePieces = board.getSortedColorPiecesByPoint(Color.WHITE);

        //then
        assertThat(whitePieces.get(0)).isEqualTo(Piece.createWhiteQueen());
        for (int i = 0; i < 2; i++)
            assertThat(whitePieces.get(1 + i)).isEqualTo(Piece.createWhiteRook());
        for (int i = 0; i < 2; i++)
            assertThat(whitePieces.get(3 + i)).isEqualTo(Piece.createWhiteBishop());
        for (int i = 0; i < 2; i++)
            assertThat(whitePieces.get(5 + i)).isEqualTo(Piece.createWhiteKnight());
        for (int i = 0; i < 8; i++)
            assertThat(whitePieces.get(7 + i)).isEqualTo(Piece.createWhitePawn());
        assertThat(whitePieces.get(whitePieces.size() - 1)).isEqualTo(Piece.createWhiteKing());
    }

    @Test
    @DisplayName("검은색 기물들을 점수를 기준으로 내림차순 정렬")
    void sortBlackPieceByPointDecrease() {
        //given
        board.initialize();

        //when
        List<Piece> whitePieces = board.getSortedColorPiecesByPoint(Color.BLACK);

        //then
        assertThat(whitePieces.get(0)).isEqualTo(Piece.createBlackQueen());
        for (int i = 0; i < 2; i++)
            assertThat(whitePieces.get(1 + i)).isEqualTo(Piece.createBlackRook());
        for (int i = 0; i < 2; i++)
            assertThat(whitePieces.get(3 + i)).isEqualTo(Piece.createBlackBishop());
        for (int i = 0; i < 2; i++)
            assertThat(whitePieces.get(5 + i)).isEqualTo(Piece.createBlackKnight());
        for (int i = 0; i < 8; i++)
            assertThat(whitePieces.get(7 + i)).isEqualTo(Piece.createBlackPawn());
        assertThat(whitePieces.get(whitePieces.size() - 1)).isEqualTo(Piece.createBlackKing());
    }

    private void addPiece(String position, Piece piece) {
        board.move(position, piece);
    }
}