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

    @Test
    void 음료_메뉴만_주문한_경우_예외가_발생한다() {
        // given
        Order order1 = Order.of("제로콜라", 1);
        Order order2 = Order.of("레드와인", 5);
        Order order3 = Order.of("샴페인", 6);

        // when & then
        assertThatThrownBy(() -> new Orders(List.of(order1, order2, order3)))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_ORDER.getMessage());
    }

    @Test
    void 메뉴의_총_개수가_20개보다_많을_경우_예외가_발생한다() {
        // given
        Order order1 = Order.of("해산물파스타", 5);
        Order order2 = Order.of("레드와인", 5);
        Order order3 = Order.of("샴페인", 7);
        Order order4 = Order.of("티본스테이크", 4);

        // when & then
        assertThatThrownBy(() -> new Orders(List.of(order1, order2, order3, order4)))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_ORDER.getMessage());
    }
}