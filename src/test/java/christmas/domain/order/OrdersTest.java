package christmas.domain.order;

import static christmas.ui.ErrorMessage.INVALID_ORDER;
import static christmas.ui.ErrorMessage.ORDERED_ONLY_BEVERAGE_MENU;
import static christmas.ui.ErrorMessage.ORDERED_OVER_MAX_MENU_COUNT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.Map;
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
                .hasMessage(ORDERED_ONLY_BEVERAGE_MENU.getMessage());
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
                .hasMessage(String.format(ORDERED_OVER_MAX_MENU_COUNT.getMessage(), 20));
    }

    @Test
    void 주문한_메뉴의_총_가격을_반환한다() {
        // given
        Order order1 = Order.of("해산물파스타", 1);
        Order order2 = Order.of("레드와인", 1);
        Order order3 = Order.of("샴페인", 2);
        Order order4 = Order.of("티본스테이크", 1);
        Orders orders = new Orders(List.of(order1, order2, order3, order4));

        // when
        int totalPrice = orders.getTotalPrice();

        // then
        assertThat(totalPrice).isEqualTo(200_000);
    }

    @Test
    void 주문을_맵으로_반환한다() {
        // given
        Order order1 = Order.of("해산물파스타", 1);
        Order order2 = Order.of("레드와인", 1);
        Order order3 = Order.of("샴페인", 2);
        Orders orders = new Orders(List.of(order1, order2, order3));

        // when
        Map<String, Integer> menuNameAndCount = orders.getMenuNameAndCount();

        // then
        assertThat(menuNameAndCount).containsExactlyInAnyOrderEntriesOf(Map.of(
                "해산물파스타", 1,
                "레드와인", 1,
                "샴페인", 2
        ));
    }
}