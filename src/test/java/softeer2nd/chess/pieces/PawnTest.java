package softeer2nd.chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.exception.IllegalMovePositionException;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class PawnTest {
    @Nested
    @DisplayName("getMovableDirection method")
    class GetMovableDirection {
        @Nested
        @DisplayName("폰이 하얀색이라면")
        class IfPawnWhite {
            @Test
            @DisplayName("흰색 폰 방향을 반환한다")
            void returnWhitePawnDirection() {
                //given
                Pawn whitePawn = new Pawn(Piece.Color.WHITE, new Position("c3"));

                //when
                List<Direction> movableDirection = whitePawn.getMovableDirection();

                //then
                List<Direction> directions = Direction.whitePawnDirection();
                assertThat(movableDirection.size()).isEqualTo(directions.size());
                for (int idx = 0; idx < movableDirection.size(); idx++) {
                    assertThat(movableDirection.get(idx))
                            .isEqualTo(directions.get(idx));
                }
            }
        }

        @Nested
        @DisplayName("폰이 검은색이라면")
        class IfPawnBlack {
            @Test
            @DisplayName("흰색 폰 방향을 반환한다")
            void returnWhitePawnDirection() {
                //given
                Pawn blackPawn = new Pawn(Piece.Color.BLACK, new Position("c3"));

                //when
                List<Direction> movableDirection = blackPawn.getMovableDirection();

                //then
                List<Direction> directions = Direction.blackPawnDirection();
                assertThat(movableDirection.size()).isEqualTo(directions.size());
                for (int idx = 0; idx < movableDirection.size(); idx++) {
                    assertThat(movableDirection.get(idx))
                            .isEqualTo(directions.get(idx));
                }
            }
        }
    }

    @Nested
    @DisplayName("isMoveDirection method")
    class IsMoveDirection {
        @Nested
        @DisplayName("앞으로 움직인다면")
        class IfMoveFront {
            @Nested
            @DisplayName("처음 움직인다면")
            class IsFirstMove {
                @Test
                @DisplayName("두 칸 까지 움직일 수 있다")
                void canMoveTwoSpace() {
                    //given
                    Pawn whitePawn = new Pawn(Piece.Color.WHITE, new Position("a1"));
                    Pawn blackPawn = new Pawn(Piece.Color.BLACK, new Position("a8"));

                    //when
                    //then
                    assertThat(whitePawn.isMovablePositionByDirection(new Position("a2"), Direction.NORTH)).isTrue();
                    assertThat(whitePawn.isMovablePositionByDirection(new Position("a3"), Direction.NORTH)).isTrue();
                    assertThat(blackPawn.isMovablePositionByDirection(new Position("a7"), Direction.SOUTH)).isTrue();
                    assertThat(blackPawn.isMovablePositionByDirection(new Position("a6"), Direction.SOUTH)).isTrue();
                }

                @Test
                @DisplayName("두 칸 넘게 움직일 수 없다")
                void cannotMoveMoreTwoSpace() {
                    //given
                    Pawn whitePawn = new Pawn(Piece.Color.WHITE, new Position("a1"));
                    Pawn blackPawn = new Pawn(Piece.Color.BLACK, new Position("a8"));

                    //when
                    //then
                    assertThat(whitePawn.isMovablePositionByDirection(new Position("a4"), Direction.NORTH)).isFalse();
                    assertThat(blackPawn.isMovablePositionByDirection(new Position("a5"), Direction.NORTH)).isFalse();
                }
            }

            @Nested
            @DisplayName("처음 움직이는게 아니라면")
            class IsNotFirstMove {
                @Test
                @DisplayName("한 칸 움직일 수 있다")
                void canMoveOneSpace() {
                    //given
                    Pawn whitePawn = new Pawn(Piece.Color.WHITE, new Position("a1"));
                    Pawn blackPawn = new Pawn(Piece.Color.BLACK, new Position("a8"));

                    //when
                    whitePawn.changePosition(new Position("a1"));
                    blackPawn.changePosition(new Position("a8"));

                    //then
                    assertThat(whitePawn.isMovablePositionByDirection(new Position("a2"), Direction.NORTH)).isTrue();
                    assertThat(blackPawn.isMovablePositionByDirection(new Position("a7"), Direction.SOUTH)).isTrue();
                }

                @Test
                @DisplayName("두 칸 이상 움직일 수 없다")
                void cannotMoveMoreTwoSpace() {
                    //given
                    Pawn whitePawn = new Pawn(Piece.Color.WHITE, new Position("a1"));
                    Pawn blackPawn = new Pawn(Piece.Color.BLACK, new Position("a8"));

                    //when
                    whitePawn.changePosition(new Position("a1"));
                    blackPawn.changePosition(new Position("a8"));

                    //then
                    assertThat(whitePawn.isMovablePositionByDirection(new Position("a3"), Direction.NORTH)).isFalse();
                    assertThat(whitePawn.isMovablePositionByDirection(new Position("a4"), Direction.NORTH)).isFalse();
                    assertThat(blackPawn.isMovablePositionByDirection(new Position("a5"), Direction.SOUTH)).isFalse();
                    assertThat(blackPawn.isMovablePositionByDirection(new Position("a6"), Direction.SOUTH)).isFalse();
                }
            }
        }

        @Nested
        @DisplayName("대각선으로 움직인다면")
        class IsMoveDiagonal {
            @Test
            @DisplayName("한 칸 움직일 수 있다")
            void canMoveOneSpace() {
                Pawn whitePawn = new Pawn(Piece.Color.WHITE, new Position("c1"));
                Pawn blackPawn = new Pawn(Piece.Color.BLACK, new Position("c8"));

                //when
                //then
                assertThat(whitePawn.isMovablePositionByDirection(new Position("d2"), Direction.NORTHEAST)).isTrue();
                assertThat(whitePawn.isMovablePositionByDirection(new Position("b2"), Direction.NORTHWEST)).isTrue();
                assertThat(blackPawn.isMovablePositionByDirection(new Position("d7"), Direction.SOUTHEAST)).isTrue();
                assertThat(blackPawn.isMovablePositionByDirection(new Position("b7"), Direction.SOUTHWEST)).isTrue();
            }

            @Test
            @DisplayName("한 칸 넘게 움직일 수 없다")
            void cannotMoveMoreOneSpace() {
                Pawn whitePawn = new Pawn(Piece.Color.WHITE, new Position("c1"));
                Pawn blackPawn = new Pawn(Piece.Color.BLACK, new Position("c8"));

                //when
                //then
                assertThat(whitePawn.isMovablePositionByDirection(new Position("e3"), Direction.NORTHEAST)).isFalse();
                assertThat(whitePawn.isMovablePositionByDirection(new Position("a3"), Direction.NORTHWEST)).isFalse();
                assertThat(blackPawn.isMovablePositionByDirection(new Position("e6"), Direction.SOUTHEAST)).isFalse();
                assertThat(blackPawn.isMovablePositionByDirection(new Position("a6"), Direction.SOUTHWEST)).isFalse();
            }
        }
    }

    @Nested
    @DisplayName("getMoveDirection method")
    class GetMoveDirection {
        @Nested
        @DisplayName("목적지에 도달할 수 있는 방향이 존재한다면")
        class IsCanArrivePositionDirectionExist {
            @Test
            @DisplayName("방향을 반환한다")
            void returnDirection() {
                //given
                Pawn pawn = new Pawn(Piece.Color.WHITE, new Position("a1"));

                //when
                Direction neDirection = pawn.getMoveDirection(new Position("b2"));

                //then
                assertThat(neDirection).isEqualTo(Direction.NORTHEAST);
            }
        }

        @Nested
        @DisplayName("목적지에 도달할 수 있는 방향이 존재하지 않다면")
        class IsCanArrivePositionDirectionNotExist {
            @Test
            @DisplayName("IllegalMovePositionException이 발생한다")
            void throwIllegalMovePositionException() {
                //given
                Pawn pawn = new Pawn(Piece.Color.WHITE, new Position("a1"));

                //when
                //then
                assertThatThrownBy(() -> pawn.getMoveDirection(new Position("b3")))
                        .isInstanceOf(IllegalMovePositionException.class);
            }
        }
    }

    @Nested
    @DisplayName("getMovePath method")
    class GetMovePath {
        @Nested
        @DisplayName("목적지에 도달할 수 있는 방향이 존재한다면")
        class IsCanArrivePositionDirectionExist {
            @Test
            @DisplayName("목적지에 도달하기 전 까지 위치들을 반환한다")
            void returnDirection() {
                //given
                Pawn pawn = new Pawn(Piece.Color.WHITE, new Position("a1"));

                //when
                List<Position> moveOncPositions = pawn.getMovePath(new Position("a2"));
                List<Position> moveTwoPositions = pawn.getMovePath(new Position("a3"));

                //then
                List<Position> exceptMoveTwoPositions = List.of(new Position("a2"));
                assertThat(moveOncPositions.isEmpty()).isTrue();
                assertThat(moveTwoPositions.size()).isEqualTo(exceptMoveTwoPositions.size());
                for (int idx = 0; idx < moveTwoPositions.size(); idx++) {
                    assertThat(moveTwoPositions.get(idx)).isEqualTo(exceptMoveTwoPositions.get(idx));
                }
            }
        }

        @Nested
        @DisplayName("목적지에 도달할 수 있는 방향이 존재하지 않다면")
        class IsCanArrivePositionDirectionNotExist {
            @Test
            @DisplayName("IllegalMovePositionException이 발생한다")
            void throwIllegalMovePositionException() {
                //given
                Pawn pawn = new Pawn(Piece.Color.WHITE, new Position("a1"));

                //when
                //then
                assertThatThrownBy(() -> pawn.getMovePath(new Position("b3")))
                        .isInstanceOf(IllegalMovePositionException.class);
            }
        }
    }
}
