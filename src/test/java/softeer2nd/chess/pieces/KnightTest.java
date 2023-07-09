package softeer2nd.chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.exception.IllegalMovePositionException;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class KnightTest {
    @Nested
    @DisplayName("getMovableDirection method")
    class GetMovableDirection {
        @Test
        @DisplayName("Knight 방향을 반환해야 한다")
        void returnKnightDirection() {
            //given
            Knight knight = new Knight(Piece.Color.WHITE, new Position("a1"));

            //when
            List<Direction> movableDirection = knight.getMovableDirection();

            //then
            List<Direction> directions = Direction.knightDirection();
            assertThat(movableDirection.size()).isEqualTo(directions.size());
            for (int idx = 0; idx < movableDirection.size(); idx++) {
                assertThat(movableDirection.get(idx))
                        .isEqualTo(directions.get(idx));
            }
        }
    }

    @Nested
    @DisplayName("isMoveDirection method")
    class IsMoveDirection {
        @Nested
        @DisplayName("주어진 위치가 주어진 방향으로 갈 수 있다면")
        class CanMoveToPositionByGivenDirection {
            @Test
            @DisplayName("true를 반환한다.")
            void returnTrue() {
                //given
                Knight knight = new Knight(Piece.Color.WHITE, new Position("a4"));

                //when
                //then
                assertThat(knight.isMovablePositionByDirection(new Position("b6"), Direction.NNE)).isTrue();
            }
        }

        @Nested
        @DisplayName("주어진 위치가 주어진 방향으로 갈 수 없다면")
        class CannotMoveToPositionByGivenDirection {
            @Test
            @DisplayName("false를 반환한다")
            void returnFalse() {
                //given
                King king = new King(Piece.Color.WHITE, new Position("a4"));

                //when
                //then
                assertThat(king.isMovablePositionByDirection(new Position("b6"), Direction.EEN)).isFalse();
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
                Knight knight = new Knight(Piece.Color.WHITE, new Position("a1"));

                //when
                Direction resultDirection = knight.getMoveDirection(new Position("c2"));

                //then
                assertThat(resultDirection).isEqualTo(Direction.EEN);
            }
        }

        @Nested
        @DisplayName("목적지에 도달할 수 있는 방향이 존재하지 않다면")
        class IsCanArrivePositionDirectionNotExist {
            @Test
            @DisplayName("IllegalMovePositionException이 발생한다")
            void throwIllegalMovePositionException() {
                //given
                Knight knight = new Knight(Piece.Color.WHITE, new Position("a1"));

                //when
                //then
                assertThatThrownBy(() -> knight.getMoveDirection(new Position("d4")))
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
                Knight knight = new Knight(Piece.Color.WHITE, new Position("a1"));

                //when
                List<Position> positions = knight.getMovePath(new Position("c2"));

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
                Knight knight = new Knight(Piece.Color.WHITE, new Position("a1"));

                //when
                //then
                assertThatThrownBy(() -> knight.getMovePath(new Position("d4")))
                        .isInstanceOf(IllegalMovePositionException.class);
            }
        }
    }
}
