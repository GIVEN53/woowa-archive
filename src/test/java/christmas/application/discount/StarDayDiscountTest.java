package christmas.application.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.application.discount.Discount;
import christmas.application.discount.StarDayDiscount;
import christmas.domain.calender.VisitDate;
import christmas.domain.order.Order;
import christmas.domain.order.Orders;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class StarDayDiscountTest {
    private final Discount starDayDiscount = new StarDayDiscount();
    private Orders orders;

    @BeforeEach
    void setUp() {
        Order order = Order.of("초코케이크", 3);
        orders = new Orders(List.of(order));
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 9, 11, 16, 18, 23, 26, 30})
    void 별이_있는_날이_아니면_할인을_적용하지_않는다(int date) {
        // given
        VisitDate visitDate = VisitDate.from(date);

        // when
        int discountAmount = starDayDiscount.calculateDiscountAmount(orders, visitDate);

        // then
        assertThat(discountAmount).isZero();
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    void 별이_있는_날이면_할인금액을_반환한다(int date) {
        // given
        VisitDate visitDate = VisitDate.from(date);

        // when
        int discountAmount = starDayDiscount.calculateDiscountAmount(orders, visitDate);

        // then
        assertThat(discountAmount).isEqualTo(1000);
    }
}