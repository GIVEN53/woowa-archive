package domain.movement;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class KingMovementValidationTest {
    @ParameterizedTest
    @EnumSource(value = Direction.class,
            names = {"UP", "DOWN", "LEFT", "RIGHT", "UP_LEFT", "UP_RIGHT", "DOWN_LEFT", "DOWN_RIGHT"})
    void 이동할_수_있는_방향이다(Direction direction) {
        MovementValidation kingMovementValidation = new KingMovementValidation();

        assertThat(kingMovementValidation.isMovable(direction)).isTrue();
    }

    @ParameterizedTest
    @EnumSource(value = Direction.class,
            names = {"UP", "DOWN", "LEFT", "RIGHT", "UP_LEFT", "UP_RIGHT", "DOWN_LEFT", "DOWN_RIGHT"},
            mode = EnumSource.Mode.EXCLUDE)
    void 이동할_수_없는_방향이다(Direction direction) {
        MovementValidation kingMovementValidation = new KingMovementValidation();

        assertThat(kingMovementValidation.isMovable(direction)).isFalse();
    }

    @Test
    void 한_칸만_이동할_수_있다() {
        int moveCount = 1;
        MovementValidation kingMovementValidation = new KingMovementValidation();

        assertThat(kingMovementValidation.isValidMoveCount(moveCount)).isTrue();
    }

    @Test
    void 두_칸_이상_이동할_수_없다() {
        int invalidMoveCount = 2;
        MovementValidation kingMovementValidation = new KingMovementValidation();

        assertThat(kingMovementValidation.isValidMoveCount(invalidMoveCount)).isFalse();
    }
}
