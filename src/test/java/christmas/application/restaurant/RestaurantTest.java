package christmas.application.restaurant;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.menu.Menu;
import christmas.domain.order.Order;
import christmas.domain.order.Orders;
import christmas.dto.Giveaway;
import java.util.List;
import org.junit.jupiter.api.Test;

class RestaurantTest {
    private final Restaurant restaurant = new Restaurant();

    @Test
    void 메뉴를_전달하면_주문을_생성한다() {
        // given
        String menu1 = "초코케이크-1";
        String menu2 = "해산물파스타-2";
        String menu3 = "레드와인-1";
        List<String> menus = List.of(menu1, menu2, menu3);

        // when
        Orders orders = restaurant.order(menus);

        // then
        assertThat(orders.getMainMenuCount()).isEqualTo(2);
        assertThat(orders.getDessertMenuCount()).isEqualTo(1);
    }

    @Test
    void 할인_전_주문금액이_12만원_이상이면_증정품을_준다() {
        // given
        Order order = Order.of("티본스테이크", 3);
        Orders orders = new Orders(List.of(order));

        // when
        Giveaway giveaway = restaurant.presentGiveaway(orders);

        // then
        assertThat(giveaway.name()).isEqualTo(Menu.CHAMPAGNE.getName());
    }

    @Test
    void 할인_전_주문금액이_12만원보다_적으면_증정품을_주지_않는다() {
        // given
        Order order = Order.of("티본스테이크", 1);
        Orders orders = new Orders(List.of(order));

        // when
        Giveaway giveaway = restaurant.presentGiveaway(orders);

        // then
        assertThat(giveaway.name()).isEqualTo("없음");
    }
}