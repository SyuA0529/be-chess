package softeer2nd.chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.exception.IllegalMovePositionException;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


class KingTest {
    @Nested
    @DisplayName("getMovableDirection은")
    class GetMovableDirection {
        @Test
        @DisplayName("모든 방향을 반환해야 한다")
        void returnEveryDirection() {
            //given
            King king = new King(Piece.Color.WHITE, new Position("a1"));

            //when
            List<Direction> movableDirection = king.getMovableDirection();

            //then
            List<Direction> directions = Direction.everyDirection();
            assertThat(movableDirection.size()).isEqualTo(directions.size());
            for (int idx = 0; idx < movableDirection.size(); idx++) {
                assertThat(movableDirection.get(idx))
                        .isEqualTo(directions.get(idx));
            }
        }
    }

    @Nested
    @DisplayName("isMoveDirection은")
    class IsMoveDirection {
        @Nested
        @DisplayName("주어진 위치가 주어진 방향으로 갈 수 있다면")
        class CanMoveToPositionByGivenDirection {
            @Test
            @DisplayName("true를 반환한다.")
            void returnTrue() {
                //given
                King king = new King(Piece.Color.WHITE, new Position("a1"));

                //when
                //then
                assertThat(king.isMovablePositionByDirection(new Position("b2"), Direction.NORTHEAST)).isTrue();
            }
        }

        @Nested
        @DisplayName("주어진 위치가 주어진 방향으로 갈 수 없다면")
        class CannotMoveToPositionByGivenDirection {
            @Test
            @DisplayName("false를 반환한다")
            void returnFalse() {
                //given
                King king = new King(Piece.Color.WHITE, new Position("a1"));

                //when
                //then
                assertThat(king.isMovablePositionByDirection(new Position("b2"), Direction.SOUTHEAST)).isFalse();
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
                King king = new King(Piece.Color.WHITE, new Position("a1"));

                //when
                Direction northDirection = king.getMoveDirection(new Position("a2"));
                Direction diagonalDirection = king.getMoveDirection(new Position("b2"));

                //then
                assertThat(northDirection).isEqualTo(Direction.NORTH);
                assertThat(diagonalDirection).isEqualTo(Direction.NORTHEAST);
            }
        }

        @Nested
        @DisplayName("목적지에 도달할 수 있는 방향이 존재하지 않다면")
        class IsCanArrivePositionDirectionNotExist {
            @Test
            @DisplayName("IllegalMovePositionException이 발생한다")
            void throwIllegalMovePositionException() {
                //given
                King king = new King(Piece.Color.WHITE, new Position("a1"));

                //when
                //then
                assertThatThrownBy(() -> king.getMoveDirection(new Position("c2")))
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
                King king = new King(Piece.Color.WHITE, new Position("a1"));

                //when
                List<Position> positions = king.getMovePath(new Position("b2"));

                //then
                assertThat(positions.isEmpty()).isTrue();
            }
        }

        @Nested
        @DisplayName("목적지에 도달할 수 있는 방향이 존재하지 않다면")
        class IsCanArrivePositionDirectionNotExist {
            @Test
            @DisplayName("IllegalMovePositionException이 발생한다")
            void throwIllegalMovePositionException() {
                //given
                King king = new King(Piece.Color.WHITE, new Position("a1"));

                //when
                //then
                assertThatThrownBy(() -> king.getMovePath(new Position("c2")))
                        .isInstanceOf(IllegalMovePositionException.class);
            }
        }
    }
}
