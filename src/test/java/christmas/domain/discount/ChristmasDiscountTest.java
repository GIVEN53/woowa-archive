package christmas.domain.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.calender.VisitDate;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class ChristmasDiscountTest {
    private final ChristmasDiscount christmasDiscount = new ChristmasDiscount();

    @ParameterizedTest
    @ValueSource(ints = {26, 27, 28, 29, 30, 31})
    void 방문날짜가_26일_이상일_경우_false를_반환한다(int day) {
        // given
        VisitDate visitDate = VisitDate.from(day);

        // when
        boolean result = christmasDiscount.isDiscountDate(visitDate);

        // then
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 4, 9, 14, 18, 23, 25})
    void 방문날짜가_25일_이하일_경우_true를_반환한다(int day) {
        // given
        VisitDate visitDate = VisitDate.from(day);

        // when
        boolean result = christmasDiscount.isDiscountDate(visitDate);

        // then
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"1,1000", "3,1200", "10,1900", "19,2800", "25,3400"})
    void 방문날짜에_따라_할인금액을_반환한다(int date, int expected) {
        // given
        VisitDate visitDate = VisitDate.from(date);

        // when
        int result = christmasDiscount.calculateDiscountAmount(visitDate);

        // then
        assertThat(result).isEqualTo(expected);
    }
}