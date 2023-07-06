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

        Position sourcePosition = new Position("b2");
        Position targetPosition = new Position("b3");
        chessGame.movePiece(sourcePosition, targetPosition);
        assertEquals(Piece.createBlank(sourcePosition), board.findPiece(sourcePosition));
        assertEquals(Piece.createWhitePawn(targetPosition), board.findPiece(targetPosition));
    }

    @Test
    @DisplayName("같은 팀 기물이 위치한 곳에는 이동 불가")
    void cannotMoveTeamPiecePos() {
        //given
        board.initializeEmpty();
        Position pos1 = new Position("b2");
        Position pos2 = new Position("b3");
        board.putPiece(pos1, Piece.createWhiteKing(pos1));
        board.putPiece(pos2, Piece.createWhiteKing(pos2));

        //when
        //then
        assertThatThrownBy(() -> chessGame.movePiece(pos1, pos2))
                .isInstanceOf(CannotMovePosException.class);
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
        assertThatThrownBy(() -> chessGame.movePiece(new Position("a1"), new Position("a2")))
                .isInstanceOf(CannotMovePieceException.class);
    }

    @Test
    @DisplayName("킹은 한칸만 움직일 수 있다")
    void moveKing() {
        //given
        board.initializeEmpty();
        Position kingPos = new Position("a1");
        board.putPiece(kingPos, Piece.createWhiteKing(kingPos));
        addPiece("b2", Piece.createBlackPawn(new Position("b2")));
        addPiece("c3", Piece.createWhitePawn(new Position("c3")));

        //when
        chessGame.movePiece(new Position("a1"), new Position("b2"));

        //then
        assertThat(board.findPiece(new Position("b2"))).isEqualTo(Piece.createWhiteKing(kingPos));
        assertThatThrownBy(() -> chessGame.movePiece(new Position("b2"), new Position("c4")))
                .isInstanceOf(CannotMovePosException.class);
        assertThatThrownBy(() -> chessGame.movePiece(new Position("b2"), new Position("c3")))
                .isInstanceOf(CannotMovePosException.class);
        assertThatThrownBy(() -> chessGame.movePiece(new Position("b2"), new Position("a0")))
                .isInstanceOf(OutOfBoardException.class);
    }

    @Test
    @DisplayName("퀸은 직선 방향이면 어디든 움직일 수 있다")
    void queenMove() {
        //given
        board.initializeEmpty();
        Piece whiteQueen = Piece.createWhiteQueen(new Position("a1"));
        addPiece("a1", whiteQueen);
        addPiece("a8", Piece.createBlackPawn(new Position("a8")));
        addPiece("h8", Piece.createWhitePawn(new Position("h8")));
        addPiece("h3", Piece.createWhitePawn(new Position("h3")));

        //when
        //then
        chessGame.movePiece(new Position("a1"), new Position("a8"));
        assertThat(board.findPiece(new Position("a8"))).isEqualTo(whiteQueen);
        chessGame.movePiece(new Position("a8"), new Position("h1"));
        assertThat(board.findPiece(new Position("h1"))).isEqualTo(whiteQueen);
        assertThatThrownBy(() -> chessGame.movePiece(new Position("h1"), new Position("h5")))
                .isInstanceOf(CannotMovePosException.class);
        assertThatThrownBy(() -> chessGame.movePiece(new Position("h1"), new Position("h8")))
                .isInstanceOf(CannotMovePosException.class);
        assertThatThrownBy(() -> chessGame.movePiece(new Position("h1"), new Position("a7")))
                .isInstanceOf(CannotMovePosException.class);
    }
    
    private void addPiece(String position, Piece piece) {
        board.putPiece(new Position(position), piece);
    }
}