package christmas.application.restaurant;

import static christmas.application.discount.EventName.*;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.application.discount.ChristmasEvent;
import christmas.application.discount.Event;
import christmas.application.discount.EventName;
import christmas.application.discount.StarDayEvent;
import christmas.application.discount.WeekdayEvent;
import christmas.application.discount.WeekendEvent;
import christmas.domain.calender.VisitDate;
import christmas.domain.menu.Menu;
import christmas.domain.order.Order;
import christmas.domain.order.Orders;
import christmas.dto.Benefits;
import christmas.dto.Giveaway;
import java.util.List;
import org.junit.jupiter.api.Test;

class RestaurantTest {
    private final List<Event> events = List.of(
            new ChristmasEvent(),
            new WeekdayEvent(),
            new WeekendEvent(),
            new StarDayEvent()
    );
    private final Restaurant restaurant = new Restaurant(events);

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

    @Test
    void 크리스마스_디데이_할인_혜택을_계산한다() {
        // given
        Order order = Order.of("티본스테이크", 2);
        Orders orders = new Orders(List.of(order));
        VisitDate visitDate = VisitDate.from(25);

        // when
        Benefits benefits = restaurant.calculateBenefit(orders, visitDate);

        // then
        assertThat(benefits.get(CHRISTMAS_EVENT.getName())).isEqualTo(3400);
    }

    @Test
    void 평일_할인_혜택을_계산한다() {
        // given
        Order order = Order.of("초코케이크", 3);
        Orders orders = new Orders(List.of(order));
        VisitDate visitDate = VisitDate.from(26);

        // when
        Benefits benefits = restaurant.calculateBenefit(orders, visitDate);

        // then
        assertThat(benefits.get(WEEKDAY_EVENT.getName())).isEqualTo(6069);
    }

    @Test
    void 주말_할인_혜택을_계산한다() {
        // given
        Order order = Order.of("티본스테이크", 2);
        Orders orders = new Orders(List.of(order));
        VisitDate visitDate = VisitDate.from(29);

        // when
        Benefits benefits = restaurant.calculateBenefit(orders, visitDate);

        // then
        assertThat(benefits.get(WEEKEND_EVENT.getName())).isEqualTo(4046);
    }

    @Test
    void 특별_할인_혜택을_계산한다() {
        // given
        Order order = Order.of("티본스테이크", 2);
        Orders orders = new Orders(List.of(order));
        VisitDate visitDate = VisitDate.from(31);

        // when
        Benefits benefits = restaurant.calculateBenefit(orders, visitDate);

        // then
        assertThat(benefits.get(STAR_DAY_EVENT.getName())).isEqualTo(1000);
    }

    @Test
    void 크리스마스_평일_특별_할인_혜택을_계산한다() {
        // given
        Order order1 = Order.of("초코케이크", 2);
        Order order2 = Order.of("티본스테이크", 2);
        Orders orders = new Orders(List.of(order1, order2));
        VisitDate visitDate = VisitDate.from(25);

        // when
        Benefits benefits = restaurant.calculateBenefit(orders, visitDate);

        // then
        assertThat(benefits.get(CHRISTMAS_EVENT.getName())).isEqualTo(3400);
        assertThat(benefits.get(WEEKDAY_EVENT.getName())).isEqualTo(4046);
        assertThat(benefits.get(STAR_DAY_EVENT.getName())).isEqualTo(1000);
    }

    @Test
    void 크리스마스_주말_할인_혜택을_계산한다() {
        // given
        Order order1 = Order.of("초코케이크", 2);
        Order order2 = Order.of("티본스테이크", 3);
        Orders orders = new Orders(List.of(order1, order2));
        VisitDate visitDate = VisitDate.from(22);

        // when
        Benefits benefits = restaurant.calculateBenefit(orders, visitDate);

        // then
        assertThat(benefits.get(CHRISTMAS_EVENT.getName())).isEqualTo(3100);
        assertThat(benefits.get(WEEKEND_EVENT.getName())).isEqualTo(6069);
    }
}