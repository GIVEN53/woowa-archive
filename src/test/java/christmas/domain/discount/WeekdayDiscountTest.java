package christmas.domain.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.calender.VisitDate;
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
}