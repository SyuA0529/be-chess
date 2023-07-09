package softeer2nd.chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.exception.IllegalMovePositionException;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class RookTest {
    @Nested
    @DisplayName("getMovableDirection method ")
    class GetMovableDirection {
        @Test
        @DisplayName("직선 방향을 반환해야 한다")
        void returnLinearDirection() {
            //given
            Rook rook = new Rook(Piece.Color.WHITE, new Position("a1"));

            //when
            List<Direction> movableDirection = rook.getMovableDirection();

            //then
            List<Direction> diagonalDirections = Direction.linearDirection();
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
                Rook rook = new Rook(Piece.Color.WHITE, new Position("a1"));

                //when
                //then
                assertThat(rook.isMovablePositionByDirection(new Position("a8"), Direction.NORTH)).isTrue();
            }
        }

        @Nested
        @DisplayName("주어진 위치가 주어진 방향으로 갈 수 없다면")
        class CannotMoveToPositionByGivenDirection {
            @Test
            @DisplayName("false를 반환한다")
            void returnFalse() {
                //given
                Rook rook = new Rook(Piece.Color.WHITE, new Position("a1"));

                //when
                //then
                assertThat(rook.isMovablePositionByDirection(new Position("a8"), Direction.SOUTH)).isFalse();
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
                Rook rook = new Rook(Piece.Color.WHITE, new Position("a1"));

                //when
                Direction northDirection = rook.getMoveDirection(new Position("a8"));

                //then
                assertThat(northDirection).isEqualTo(Direction.NORTH);
            }
        }

        @Nested
        @DisplayName("목적지에 도달할 수 있는 방향이 존재하지 않다면")
        class IsCanArrivePositionDirectionNotExist {
            @Test
            @DisplayName("IllegalMovePositionException이 발생한다")
            void throwIllegalMovePositionException() {
                //given
                Rook rook = new Rook(Piece.Color.WHITE, new Position("a1"));

                //when
                //then
                assertThatThrownBy(() -> rook.getMoveDirection(new Position("h8")))
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
                Rook rook = new Rook(Piece.Color.WHITE, new Position("a1"));

                //when
                List<Position> positions = rook.getMovePath(new Position("a8"));

                //then
                List<Position> expectPositions = List.of(
                        new Position("a2"), new Position("a3"), new Position("a4"),
                        new Position("a5"), new Position("a6"), new Position("a7")
                );
                assertThat(positions.size()).isEqualTo(expectPositions.size());
                for (int idx = 0; idx < positions.size(); idx++) {
                    assertThat(positions.get(idx)).isEqualTo(expectPositions.get(idx));
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
                Rook rook = new Rook(Piece.Color.WHITE, new Position("a1"));

                //when
                //then
                assertThatThrownBy(() -> rook.getMovePath(new Position("h8")))
                        .isInstanceOf(IllegalMovePositionException.class);
            }
        }
    }
}
