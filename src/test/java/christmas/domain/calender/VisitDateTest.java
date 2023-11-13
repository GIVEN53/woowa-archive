package christmas.domain.calender;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class VisitDateTest {
    static List<Integer> provideWeekdays() {
        return IntStream.rangeClosed(3, 31)
                .filter(day -> day % 7 != 1 && day % 7 != 2)
                .boxed()
                .toList();
    }

    static List<Integer> provideWeekends() {
        return IntStream.rangeClosed(3, 31)
                .filter(day -> day % 7 == 1 || day % 7 == 2)
                .boxed()
                .toList();
    }

    @ParameterizedTest(name = "12월 {arguments}일")
    @MethodSource("provideWeekdays")
    void 평일이면_true를_반환한다(int date) {
        // when
        VisitDate visitDate = VisitDate.from(date);

        // then
        assertThat(visitDate.isWeekDay()).isTrue();
    }

    @ParameterizedTest(name = "12월 {arguments}일")
    @MethodSource("provideWeekends")
    void 평일이_아니면_false를_반환한다(int date) {
        // when
        VisitDate visitDate = VisitDate.from(date);

        // then
        assertThat(visitDate.isWeekDay()).isFalse();
    }
}