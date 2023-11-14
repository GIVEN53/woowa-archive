package christmas.application.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.application.discount.ChristmasDiscount;
import christmas.application.discount.Discount;
import christmas.domain.calender.VisitDate;
import christmas.domain.order.Order;
import christmas.domain.order.Orders;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class ChristmasDiscountTest {
    private final Discount christmasDiscount = new ChristmasDiscount();
    private Orders orders;

    @BeforeEach
    void setUp() {
        Order order = Order.of("해산물파스타", 1);
        orders = new Orders(List.of(order));
    }

    @ParameterizedTest
    @ValueSource(ints = {26, 27, 28, 29, 30, 31})
    void 방문날짜가_26일_이상일_경우_할인을_적용하지_않는다(int date) {
        // given
        VisitDate visitDate = VisitDate.from(date);

        // when
        int discountAmount = christmasDiscount.calculateDiscountAmount(orders, visitDate);

        // then
        assertThat(discountAmount).isZero();
    }

    @ParameterizedTest
    @CsvSource({"1,1000", "3,1200", "10,1900", "19,2800", "25,3400"})
    void 방문날짜가_25일_이하일_경우_방문날짜에_따라_할인금액을_반환한다(int date, int expected) {
        // given
        VisitDate visitDate = VisitDate.from(date);

        // when
        int discountAmount = christmasDiscount.calculateDiscountAmount(orders, visitDate);

        // then
        assertThat(discountAmount).isEqualTo(expected);
    }
}