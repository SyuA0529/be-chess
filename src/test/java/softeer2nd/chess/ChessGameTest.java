package softeer2nd.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.board.Board;
import softeer2nd.chess.exception.IllegalMovePieceException;
import softeer2nd.chess.exception.IllegalMovePositionException;
import softeer2nd.chess.exception.IllegalTurnException;
import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Piece.Type;
import softeer2nd.chess.pieces.Piece.Color;
import softeer2nd.chess.pieces.Position;


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

    @Nested
    @DisplayName("movePiece method")
    class MovePiece {
        @Nested
        @DisplayName("기물이 공백이라면")
        class IsPieceBlank {
            @Test
            @DisplayName("IllegalMovePieceException이 발생한다")
            void throwIllegalMovePieceException() {
                //given
                board.initializeEmpty();

                //when
                //then
                assertThatThrownBy(() -> chessGame.movePiece(new Position("a1"), new Position("a2")))
                        .isInstanceOf(IllegalMovePieceException.class);
            }
        }

        @Nested
        @DisplayName("기물이 공백이 아니라면")
        class IsPieceNotBlank {
            @Nested
            @DisplayName("올바른 이동일 때 턴이 흰색이라면")
            class IsTurnWhite {
                @Test
                @DisplayName("검은색 기물을 움직이면 IllegalTurnException이 발생한다")
                void cannotMoveBlackPiece() {
                    //given
                    board.initialize();

                    //when
                    //then
                    assertThatThrownBy(() -> chessGame.movePiece(new Position("a7"), new Position("a6")))
                            .isInstanceOf(IllegalTurnException.class);
                }
            }

            @Nested
            @DisplayName("올바른 이동일 때 턴이 검은색이라면")
            class IsTurnBlack {
                @Test
                @DisplayName("흰색 기물을 움직이면 IllegalTurnException이 발생한다")
                void cannotMoveWhitePiece() {
                    //given
                    board.initialize();
                    chessGame.movePiece(new Position("a2"), new Position("a3"));

                    //when
                    //then
                    assertThatThrownBy(() -> chessGame.movePiece(new Position("b2"), new Position("b3")))
                            .isInstanceOf(IllegalTurnException.class);
                }
            }

            @Nested
            @DisplayName("목적지가 기물이 이동할 수 있는 위치라면")
            class IsPieceCanMoveToPosition {
                @Nested
                @DisplayName("목적지에 같은 팀 기물이 존재한다면")
                class IsSameTeamPieceInTargetPosition {
                    @Test
                    @DisplayName("IllegalMovePositionException이 발생한다")
                    void throwIllegalMovePositionException() {
                        //given
                        board.initializeEmpty();
                        addPiece(Color.WHITE, Type.QUEEN, "a1");
                        addPiece(Color.WHITE, Type.BISHOP, "a8");

                        //when
                        //then

                        assertThatThrownBy(() -> chessGame.movePiece(new Position("a1"), new Position("a8")))
                                .isInstanceOf(IllegalMovePositionException.class);
                    }
                }

                @Nested
                @DisplayName("목적지에 같은 팀 기물이 존재하지 않다면")
                class IsSameTeamPieceNotInTargetPosition {

                    @Nested
                    @DisplayName("목적지까지 경로에 다른 기물이 존재한다면")
                    class IsPieceInPath {
                        @Test
                        @DisplayName("IllegalMovePositionException이 발생한다")
                        void throwIllegalMovePositionException() {
                            //given
                            board.initializeEmpty();
                            addPiece(Color.WHITE, Type.QUEEN, "a1");
                            addPiece(Color.BLACK, Type.PAWN, "a4");

                            //when
                            //then
                            assertThatThrownBy(() -> chessGame.movePiece(new Position("a1"), new Position("a8")))
                                    .isInstanceOf(IllegalMovePositionException.class);
                        }
                    }

                    @Nested
                    @DisplayName("목적지까지 경로에 다른 기물이 존재하지 않는다면")
                    class IsPieceNotInpath {
                        @Nested
                        @DisplayName("기물이 폰이라면")
                        class IsPiecePawn {
                            @Nested
                            @DisplayName("이동이 직선이라면")
                            class IsMoveLinear {
                                @Nested
                                @DisplayName("목적지에 기물이 있다면")
                                class IsPieceInPosition {
                                    @Test
                                    @DisplayName("IllegalMovePositionException이 발생한다")
                                    void throwIllegalMovePositionException() {
                                        //given
                                        board.initializeEmpty();
                                        addPiece(Color.WHITE, Type.PAWN, "a1");
                                        addPiece(Color.BLACK, Type.PAWN, "a2");

                                        //when
                                        //then
                                        assertThatThrownBy(() -> chessGame.movePiece(new Position("a1"), new Position("a2")))
                                                .isInstanceOf(IllegalMovePositionException.class);
                                    }
                                }

                                @Nested
                                @DisplayName("목적지에 기물이 없다면")
                                class IsPieceNotInPosition {
                                    @Test
                                    @DisplayName("목적지로 이동한다")
                                    void moveToPosition() {
                                        //given
                                        board.initializeEmpty();
                                        addPiece(Color.WHITE, Type.PAWN, "a1");

                                        //when
                                        chessGame.movePiece(new Position("a1"), new Position("a2"));

                                        //then
                                        assertThat(board.findPiece(new Position("a2")))
                                                .isEqualTo(createNotBlank(Color.WHITE, Type.PAWN, new Position("a2")));
                                    }
                                }
                            }

                            @Nested
                            @DisplayName("이동이 대각선이라면")
                            class IsMoveDiagonal {
                                @Nested
                                @DisplayName("목적지에 다른 팀 기물이 있다면")
                                class IsAnotherTeamPieceInPosition {
                                    @Test
                                    @DisplayName("목적지로 이동한다")
                                    void moveToPosition() {
                                        board.initializeEmpty();
                                        addPiece(Color.WHITE, Type.PAWN, "a1");
                                        addPiece(Color.BLACK, Type.PAWN, "b2");

                                        //when
                                        chessGame.movePiece(new Position("a1"), new Position("b2"));

                                        //then
                                        assertThat(board.findPiece(new Position("b2")))
                                                .isEqualTo(createNotBlank(Color.WHITE, Type.PAWN, new Position("b2")));
                                    }
                                }

                                @Nested
                                @DisplayName("목적지에 다른 팀 기물이 없다면")
                                class IsAnotherTeamPieceNotInPosition {
                                    @Test
                                    @DisplayName("IllegalMovePositionException이 발생한다")
                                    void throwIllegalMovePositionException() {

                                        //given
                                        board.initializeEmpty();
                                        addPiece(Color.WHITE, Type.PAWN, "a1");

                                        //when
                                        //then
                                        assertThatThrownBy(() -> chessGame.movePiece(new Position("a1"), new Position("b2")))
                                                .isInstanceOf(IllegalMovePositionException.class);
                                    }
                                }
                            }
                        }

                        @Nested
                        @DisplayName("기물이 폰이 아니라면")
                        class IsPieceNotPawn {
                            @Test
                            @DisplayName("목적지로 이동한다")
                            void moveToPosition() {
                                //given
                                board.initializeEmpty();
                                addPiece(Color.WHITE, Type.QUEEN, "a1");

                                //when
                                chessGame.movePiece(new Position("a1"), new Position("a8"));

                                //then
                                assertThat(board.findPiece(new Position("a8")))
                                        .isEqualTo(createNotBlank(Color.WHITE, Type.QUEEN, new Position("a8")));
                            }
                        }
                    }
                }
            }

            @Nested
            @DisplayName("목적지가 기물이 이동할 수 없는 위치라면")
            class IsPieceCannotMoveToPosition {
                @Test
                @DisplayName("IllegalMovePositionException이 발생한다")
                void throwIllegalMovePositionException() {
                    //given
                    board.initializeEmpty();
                    addPiece(Color.WHITE, Type.QUEEN, "a1");

                    //when
                    //then

                    assertThatThrownBy(() -> chessGame.movePiece(new Position("a1"), new Position("b8")))
                            .isInstanceOf(IllegalMovePositionException.class);
                }
            }
        }
    }


    @Nested
    @DisplayName("calculatePoint method")
    class CalculatePoint {
        @Test
        @DisplayName("현재 보드에서 주어진 색상의 점수를 계산한다")
        void calculatePointByColorInCurBoard() {
            //given
            board.initializeEmpty();

            addPiece(Color.BLACK, Type.PAWN, "b6");
            addPiece(Color.BLACK, Type.QUEEN, "e6");
            addPiece(Color.BLACK, Type.KING, "b8");
            addPiece(Color.BLACK, Type.ROOK, "c8");

            addPiece(Color.WHITE, Type.PAWN, "f2");
            addPiece(Color.WHITE, Type.PAWN, "g2");
            addPiece(Color.WHITE, Type.ROOK, "e1");
            addPiece(Color.WHITE, Type.KING, "f1");

            //when
            //then
            assertEquals(15.0, chessGame.calculatePoint(Color.BLACK), 0.01);
            assertEquals(7.0, chessGame.calculatePoint(Color.WHITE), 0.01);
        }
    }

    private void addPiece(Color color, Type type, String position) {
        Piece piece = type.equals(Type.NO_PIECE) ?
                createBlank(new Position(position)): createNotBlank(color, type, new Position(position));
        board.putPiece(new Position(position), piece);
    }
}
