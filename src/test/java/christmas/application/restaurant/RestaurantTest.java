package christmas.application.restaurant;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.order.Orders;
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
}