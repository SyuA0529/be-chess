package softeer2nd.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.board.Board;
import softeer2nd.chess.exception.CannotMovePieceException;
import softeer2nd.chess.exception.CannotMovePosException;
import softeer2nd.chess.exception.OutOfBoardException;
import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Piece.Type;
import softeer2nd.chess.pieces.Piece.Color;
import softeer2nd.chess.pieces.Position;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static softeer2nd.chess.pieces.PieceFactory.*;

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
        assertEquals(createBlank(sourcePosition), board.findPiece(sourcePosition));
        assertEquals(createNotBlank(Color.WHITE, Type.PAWN, targetPosition), board.findPiece(targetPosition));
    }

    @Test
    @DisplayName("같은 팀 기물이 위치한 곳에는 이동 불가")
    void cannotMoveTeamPiecePos() {
        //given
        board.initializeEmpty();
        List<Position> positions = List.of(new Position("b2"), new Position("b3"));
        for (Position pos : positions)
            board.putPiece(pos, createNotBlank(Color.WHITE, Type.KING, pos));

        //when
        //then
        assertThatThrownBy(() -> chessGame.movePiece(positions.get(0), positions.get(1)))
                .isInstanceOf(CannotMovePosException.class);
    }

    @Test
    @DisplayName("기물 점수 계산")
    void calculatePoint() {
        board.initializeEmpty();

        addPiece(Color.BLACK, Type.PAWN, "b6");
        addPiece(Color.BLACK, Type.QUEEN, "e6");
        addPiece(Color.BLACK, Type.KING, "b8");
        addPiece(Color.BLACK, Type.ROOK, "c8");

        addPiece(Color.WHITE, Type.PAWN, "f2");
        addPiece(Color.WHITE, Type.PAWN, "g2");
        addPiece(Color.WHITE, Type.ROOK, "e1");
        addPiece(Color.WHITE, Type.KING, "f1");

        assertEquals(15.0, chessGame.calculatePoint(Color.BLACK), 0.01);
        assertEquals(7.0, chessGame.calculatePoint(Color.WHITE), 0.01);
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
        addPiece(Color.WHITE, Type.KING, "a1");
        addPiece(Color.BLACK, Type.PAWN, "b2");
        addPiece(Color.WHITE, Type.PAWN, "c3");

        //when
        chessGame.movePiece(new Position("a1"), new Position("b2"));

        //then
        assertThat(board.findPiece(new Position("b2"))).isEqualTo(
                createNotBlank(Color.WHITE, Type.KING, new Position("b2")));
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

        addPiece(Color.WHITE, Type.QUEEN, "a1");
        addPiece(Color.BLACK, Type.PAWN, "a8");
        addPiece(Color.WHITE, Type.PAWN, "h8");
        addPiece(Color.WHITE, Type.PAWN, "h3");

        //when
        //then
        Piece whiteQueen = board.findPiece(new Position("a1"));
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
    
    private void addPiece(Color color, Type type, String position) {
        Piece piece = type.equals(Type.NO_PIECE) ?
                createBlank(new Position(position)): createNotBlank(color, type, new Position(position));
        board.putPiece(new Position(position), piece);
    }
}