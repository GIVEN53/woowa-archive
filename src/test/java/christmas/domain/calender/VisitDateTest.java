package christmas.domain.calender;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class VisitDateTest {
    private static final String TEST_NAME = "12월 {arguments}일";

    static List<Integer> provideWeekday() {
        return IntStream.rangeClosed(3, 31)
                .filter(day -> day % 7 != 1 && day % 7 != 2)
                .boxed()
                .toList();
    }

    static List<Integer> provideWeekend() {
        return IntStream.rangeClosed(3, 31)
                .filter(day -> day % 7 == 1 || day % 7 == 2)
                .boxed()
                .toList();
    }

    @ParameterizedTest(name = TEST_NAME)
    @MethodSource("provideWeekday")
    void 평일이면_true를_반환한다(int date) {
        // when
        VisitDate visitDate = VisitDate.from(date);

        // then
        assertThat(visitDate.isWeekday()).isTrue();
    }

    @ParameterizedTest(name = TEST_NAME)
    @MethodSource("provideWeekend")
    void 평일이_아니면_false를_반환한다(int date) {
        // when
        VisitDate visitDate = VisitDate.from(date);

        // then
        assertThat(visitDate.isWeekday()).isFalse();
    }

    @ParameterizedTest(name = TEST_NAME)
    @MethodSource("provideWeekend")
    void 주말이면_true를_반환한다(int date) {
        // when
        VisitDate visitDate = VisitDate.from(date);

        // then
        assertThat(visitDate.isWeekend()).isTrue();
    }

    @ParameterizedTest(name = TEST_NAME)
    @MethodSource("provideWeekday")
    void 주말이_아니면_false를_반환한다(int date) {
        // when
        VisitDate visitDate = VisitDate.from(date);

        // then
        assertThat(visitDate.isWeekend()).isFalse();
    }

    @ParameterizedTest(name = TEST_NAME)
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    void 별이_있는_날이면_true를_반환한다(int date) {
        // when
        VisitDate visitDate = VisitDate.from(date);

        // then
        assertThat(visitDate.isStarDay()).isTrue();
    }

    @ParameterizedTest(name = TEST_NAME)
    @ValueSource(ints = {2, 4, 9, 11, 16, 18, 23, 26, 30})
    void 별이_없는_날이면_false를_반환한다(int date) {
        // when
        VisitDate visitDate = VisitDate.from(date);

        // then
        assertThat(visitDate.isStarDay()).isFalse();
    }

    @ParameterizedTest(name = TEST_NAME)
    @ValueSource(ints = {0, -1, 32, 33})
    void 유효하지_않은_날짜로_객체를_생성하면_예외가_발생한다(int invalidDate) {
        // when
        assertThatThrownBy(() -> VisitDate.from(invalidDate))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }
}