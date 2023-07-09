package softeer2nd.chess.pieces;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.exception.IllegalMovePositionException;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class QueenTest {
    @Nested
    @DisplayName("getMovableDirection method ")
    class GetMovableDirection {
        @Test
        @DisplayName("모든 방향을 반환해야 한다")
        void returnEveryDirection() {
            //given
            Queen queen = new Queen(Piece.Color.WHITE, new Position("a1"));

            //when
            List<Direction> movableDirection = queen.getMovableDirection();

            //then
            List<Direction> diagonalDirections = Direction.everyDirection();
            assertThat(movableDirection.size()).isEqualTo(diagonalDirections.size());
            for (int idx = 0; idx < movableDirection.size(); idx++) {
                assertThat(movableDirection.get(idx)).isEqualTo(diagonalDirections.get(idx));
            }
        }
    }

    @Nested
    @DisplayName("isMoveDirection method")
    class IsMoveDirection {
        @Nested
        @DisplayName("주어진 위치에 주어진 방향으로 갈 수 있다면")
        class CanMoveToPositionByGivenDirection {
            @Test
            @DisplayName("true를 반환한다.")
            void returnTrue() {
                //given
                Queen queen = new Queen(Piece.Color.WHITE, new Position("a1"));

                //when
                //then
                assertThat(queen.isMovablePositionByDirection(new Position("h8"), Direction.NORTHEAST)).isTrue();
                assertThat(queen.isMovablePositionByDirection(new Position("a8"), Direction.NORTH)).isTrue();
            }
        }

        @Nested
        @DisplayName("주어진 위치가 주어진 방향으로 갈 수 없다면")
        class CannotMoveToPositionByGivenDirection {
            @Test
            @DisplayName("false를 반환한다")
            void returnFalse() {
                //given
                Queen queen = new Queen(Piece.Color.WHITE, new Position("a1"));

                //when
                //then
                assertThat(queen.isMovablePositionByDirection(new Position("h8"), Direction.SOUTHEAST)).isFalse();
                assertThat(queen.isMovablePositionByDirection(new Position("a8"), Direction.SOUTH)).isFalse();
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
                Queen queen = new Queen(Piece.Color.WHITE, new Position("a1"));

                //when
                Direction linearDirection = queen.getMoveDirection(new Position("a8"));
                Direction diaognalDirection = queen.getMoveDirection(new Position("h8"));

                //then
                assertThat(linearDirection).isEqualTo(Direction.NORTH);
                assertThat(diaognalDirection).isEqualTo(Direction.NORTHEAST);
            }
        }

        @Nested
        @DisplayName("목적지에 도달할 수 있는 방향이 존재하지 않다면")
        class IsCanArrivePositionDirectionNotExist {
            @Test
            @DisplayName("IllegalMovePositionException이 발생한다")
            void throwIllegalMovePositionException() {
                //given
                Queen queen = new Queen(Piece.Color.WHITE, new Position("a1"));

                //when
                //then
                assertThatThrownBy(() -> queen.getMoveDirection(new Position("d8")))
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
                Queen queen = new Queen(Piece.Color.WHITE, new Position("a1"));

                //when
                List<Position> diagonalPositions = queen.getMovePath(new Position("h8"));
                List<Position> linearPositions = queen.getMovePath(new Position("a8"));

                //then
                List<Position> expectDiagonalPositions = List.of(
                        new Position("b2"), new Position("c3"), new Position("d4"),
                        new Position("e5"), new Position("f6"), new Position("g7")
                );
                assertThat(diagonalPositions.size()).isEqualTo(expectDiagonalPositions.size());
                for (int idx = 0; idx < diagonalPositions.size(); idx++) {
                    assertThat(diagonalPositions.get(idx)).isEqualTo(expectDiagonalPositions.get(idx));
                }

                List<Position> expectLinearPositions = List.of(
                        new Position("a2"), new Position("a3"), new Position("a4"),
                        new Position("a5"), new Position("a6"), new Position("a7")
                );
                assertThat(linearPositions.size()).isEqualTo(expectLinearPositions.size());
                for (int idx = 0; idx < diagonalPositions.size(); idx++) {
                    assertThat(linearPositions.get(idx)).isEqualTo(expectLinearPositions.get(idx));
                }
            }

            @Nested
            @DisplayName("목적지에 도달할 수 있는 방향이 존재하지 않다면")
            class IsCanArrivePositionDirectionNotExist {
                @Test
                @DisplayName("IllegalMovePositionException이 발생한다")
                void throwIllegalMovePositionException() {
                    //given
                    Queen queen = new Queen(Piece.Color.WHITE, new Position("a1"));

                    //when
                    //then
                    assertThatThrownBy(() -> queen.getMovePath(new Position("d8")))
                            .isInstanceOf(IllegalMovePositionException.class);
                }
            }
        }
    }
}
