package softeer2nd.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.board.Board;
import softeer2nd.chess.exception.CannotMovePieceException;
import softeer2nd.chess.exception.CannotMovePosException;
import softeer2nd.chess.exception.OutOfBoardException;
import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Position;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ChessGameTest {
    Board board;
    ChessGame chessGame;

    @BeforeEach
    void setUp() {
        board = new Board();
        chessGame = new ChessGame(board);
    }

    @Test
    @DisplayName("기물 이동")
    void movePiece() {
        board.initialize();

        String sourcePosition = "b2";
        String targetPosition = "b3";
        chessGame.movePiece(sourcePosition, targetPosition);
        assertEquals(Piece.createBlank(new Position(sourcePosition)), board.findPiece(sourcePosition));
        assertEquals(Piece.createWhitePawn(new Position(targetPosition)), board.findPiece(targetPosition));
    }

    @Test
    @DisplayName("기물 점수 계산")
    void calculatePoint() {
        board.initializeEmpty();

        addPiece("b6", Piece.createBlackPawn(new Position("b6")));
        addPiece("e6", Piece.createBlackQueen(new Position("e6")));
        addPiece("b8", Piece.createBlackKing(new Position("b8")));
        addPiece("c8", Piece.createBlackRook(new Position("c8")));

        addPiece("f2", Piece.createWhitePawn(new Position("f2")));
        addPiece("g2", Piece.createWhitePawn(new Position("g2")));
        addPiece("e1", Piece.createWhiteRook(new Position("e1")));
        addPiece("f1", Piece.createWhiteKing(new Position("f1")));

        assertEquals(15.0, chessGame.calculatePoint(Piece.Color.BLACK), 0.01);
        assertEquals(7.0, chessGame.calculatePoint(Piece.Color.WHITE), 0.01);
    }

    @Test
    @DisplayName("빈 기물은 움직일 수 없다")
    void cannotMoveBlankPiece() {
        //given
        board.initializeEmpty();

        //when
        //then
        assertThatThrownBy(() -> chessGame.movePiece("a1", "a2"))
                .isInstanceOf(CannotMovePieceException.class);
    }

    @Test
    @DisplayName("킹은 한칸만 움직일 수 있다")
    void moveKing() {
        //given
        board.initializeEmpty();
        String kingPos = "a1";
        Piece king = Piece.createWhiteKing(new Position(kingPos));
        board.putPiece(kingPos, king);
        board.putPiece("b2", Piece.createBlackPawn(new Position("b2")));
        board.putPiece("c3", Piece.createWhitePawn(new Position("c3")));

        //when
        chessGame.movePiece("a1", "b2");

        //then
        assertThat(board.findPiece("b2")).isEqualTo(king);
        assertThatThrownBy(() -> chessGame.movePiece("b2", "c4"))
                .isInstanceOf(CannotMovePosException.class);
        assertThatThrownBy(() -> chessGame.movePiece("b2", "c3"))
                .isInstanceOf(CannotMovePosException.class);
        assertThatThrownBy(() -> chessGame.movePiece("b2", "a0"))
                .isInstanceOf(OutOfBoardException.class);
    }
    
    private void addPiece(String position, Piece piece) {
        board.putPiece(position, piece);
    }
}