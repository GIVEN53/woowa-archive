package christmas.domain.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.calender.VisitDate;
import christmas.domain.order.Order;
import christmas.domain.order.Orders;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class WeekdayDiscountTest {
    private final WeekdayDiscount weekdayDiscount = new WeekdayDiscount();

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23, 29, 30})
    void 평일이_아니면_false를_반환한다(int date) {
        // given
        VisitDate visitDate = VisitDate.from(date);

        // when
        boolean result = weekdayDiscount.isDiscountDate(visitDate);

        // then
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 7, 10, 14, 17, 21, 24, 28, 31})
    void 평일이면_true를_반환한다(int date) {
        // given
        VisitDate visitDate = VisitDate.from(date);

        // when
        boolean result = weekdayDiscount.isDiscountDate(visitDate);

        // then
        assertThat(result).isTrue();
    }

    @Test
    void 디저트_메뉴_개수에_따라_할인금액을_반환한다() {
        // given
        int dessertCount = 3;
        Order order = Order.of("초코케이크", dessertCount);
        Orders orders = new Orders(List.of(order));

        // when
        int result = weekdayDiscount.calculateDiscountAmount(orders);

        // then
        assertThat(result).isEqualTo(6069);
    }
}