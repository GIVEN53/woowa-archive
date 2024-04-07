package christmas.application.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.calender.VisitDate;
import christmas.domain.order.Order;
import christmas.domain.order.Orders;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class WeekendEventTest {
    private final Event weekendEvent = new WeekendEvent();
    private Orders orders;

    @BeforeEach
    void setUp() {
        Order order = Order.of("티본스테이크", 3);
        orders = new Orders(List.of(order));
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 7, 10, 14, 17, 21, 24, 28, 31})
    void 주말이_아니면_할인을_적용하지_않는다(int date) {
        // given
        VisitDate visitDate = VisitDate.from(date);

        // when
        int discountAmount = weekendEvent.applyDiscount(orders, visitDate);

        // then
        assertThat(discountAmount).isZero();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23, 29, 30})
    void 주말이면_메인_메뉴_개수에_따라_할인금액을_반환한다(int date) {
        // given
        VisitDate visitDate = VisitDate.from(date);

        // when
        int discountAmount = weekendEvent.applyDiscount(orders, visitDate);

        // then
        assertThat(discountAmount).isEqualTo(6069);
    }

    @Test
    void 주말이지만_메인_메뉴가_아니면_할인을_적용하지_않는다() {
        // given
        Order order = Order.of("초코케이크", 1);
        Orders orders = new Orders(List.of(order));
        VisitDate visitDate = VisitDate.from(3);

        // when
        int discountAmount = weekendEvent.applyDiscount(orders, visitDate);

        // then
        assertThat(discountAmount).isZero();
    }
}