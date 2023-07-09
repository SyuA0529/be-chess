package softeer2nd.chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.exception.IllegalMovePieceException;


import static org.assertj.core.api.Assertions.*;


class BlankTest {
    @Nested
    @DisplayName("getMovableDirection method")
    class GetMovableDirection {
        @Test
        @DisplayName("IllegalMovePieceException을 반환한다")
        void throwIllegalMovePieceException() {
            //given
            Blank blank = new Blank(new Position("a1"));

            //when
            //then
            assertThatThrownBy(blank::getMovableDirection).isInstanceOf(IllegalMovePieceException.class);
        }
    }

    @Nested
    @DisplayName("isMoveDirection method")
    class IsMoveDirection {
        @Test
        @DisplayName("IllegalMovePieceException을 발생시킨다")
        void throwIllegalMovePieceException() {
            //given
            Blank blank = new Blank(new Position("a1"));
            //when
            //then
            assertThatThrownBy(() -> blank.isMovablePositionByDirection(new Position("a2"), Direction.NNE)).isInstanceOf(IllegalMovePieceException.class);
        }

    }

    @Nested
    @DisplayName("getMoveDirection method")
    class GetMoveDirection {
        @Test
        @DisplayName("IllegalMovePieceException이 발생한다")
        void throwIllegalMovePieceException() {
            //given
            Blank blank = new Blank(new Position("a1"));

            //when
            //then
            assertThatThrownBy(() -> blank.getMoveDirection(new Position("a2")))
                    .isInstanceOf(IllegalMovePieceException.class);
        }
    }

    @Nested
    @DisplayName("getMovePath method")
    class GetMovePath {
        @Test
        @DisplayName("IllegalMovePieceException이 발생한다")
        void throwIllegalMovePieceException() {
            //given
            Blank blank = new Blank(new Position("a1"));

            //when
            //then
            assertThatThrownBy(() -> blank.getMovePath(new Position("a2")))
                    .isInstanceOf(IllegalMovePieceException.class);
        }
    }
}
