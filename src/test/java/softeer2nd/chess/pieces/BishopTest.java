package softeer2nd.chess.pieces;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.exception.IllegalMovePositionException;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class BishopTest {
    @Nested
    @DisplayName("getMovableDirection method ")
    class GetMovableDirection {
        @Test
        @DisplayName("대각선 방향을 반환해야 한다")
        void returnDiagonalDirection() {
            //given
            Bishop bishop = new Bishop(Piece.Color.WHITE, new Position("a1"));

            //when
            List<Direction> movableDirection = bishop.getMovableDirection();

            //then
            List<Direction> diagonalDirections = Direction.diagonalDirection();
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
                Bishop bishop = new Bishop(Piece.Color.WHITE, new Position("a1"));

                //when
                boolean result = bishop.isMovablePositionByDirection(new Position("h8"), Direction.NORTHEAST);

                //then
                assertThat(result).isTrue();
            }
        }

        @Nested
        @DisplayName("주어진 위치가 주어진 방향으로 갈 수 없다면")
        class CannotMoveToPositionByGivenDirection {
            @Test
            @DisplayName("false를 반환한다")
            void returnFalse() {
                //given
                Bishop bishop = new Bishop(Piece.Color.WHITE, new Position("a1"));

                //when
                //then
                assertThat(bishop.isMovablePositionByDirection(new Position("h8"), Direction.SOUTHEAST)).isFalse();
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
                Bishop bishop = new Bishop(Piece.Color.WHITE, new Position("a1"));

                //when
                Direction resultDirection = bishop.getMoveDirection(new Position("h8"));

                //then
                assertThat(resultDirection).isEqualTo(Direction.NORTHEAST);
            }
        }

        @Nested
        @DisplayName("목적지에 도달할 수 있는 방향이 존재하지 않다면")
        class IsCanArrivePositionDirectionNotExist {
            @Test
            @DisplayName("IllegalMovePositionException이 발생한다")
            void throwIllegalMovePositionException() {
                //given
                Bishop bishop = new Bishop(Piece.Color.WHITE, new Position("a1"));

                //when
                //then
                assertThatThrownBy(() -> bishop.getMoveDirection(new Position("a8")))
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
                Bishop bishop = new Bishop(Piece.Color.WHITE, new Position("a1"));

                //when
                List<Position> positions = bishop.getMovePath(new Position("h8"));

                //then
                List<Position> expectPositions = List.of(
                        new Position("b2"), new Position("c3"), new Position("d4"),
                        new Position("e5"), new Position("f6"), new Position("g7")
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
                Bishop bishop = new Bishop(Piece.Color.WHITE, new Position("a1"));

                //when
                //then
                assertThatThrownBy(() -> bishop.getMovePath(new Position("a8")))
                        .isInstanceOf(IllegalMovePositionException.class);
            }
        }
    }
}
