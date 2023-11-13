package christmas.domain.order;

import static christmas.ui.ErrorMessage.INVALID_ORDER;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

class OrdersTest {
    @Test
    void 중복된_메뉴가_존재하면_예외가_발생한다() {
        // given
        Order order1 = Order.of("해산물파스타", 1);
        Order order2 = Order.of("해산물파스타", 5);
        Order order3 = Order.of("레드와인", 1);

        // when & then
        assertThatThrownBy(() -> new Orders(List.of(order1, order2, order3)))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_ORDER.getMessage());
    }
}