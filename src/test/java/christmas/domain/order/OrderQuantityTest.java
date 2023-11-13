package christmas.domain.order;

import static christmas.ui.ErrorMessage.INVALID_ORDER;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderQuantityTest {
    @ParameterizedTest
    @ValueSource(ints = {1, 10, 20})
    void 주문한_메뉴_개수가_1이상인_경우_객체를_생성한다(int quantity) {
        // when & then
        assertDoesNotThrow(() -> new OrderQuantity(quantity));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    void 주문한_메뉴_개수가_1보다_작을_경우_예외가_발생한다(int quantity) {
        // when & then
        assertThatThrownBy(() -> new OrderQuantity(quantity))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_ORDER.getMessage());
    }
}