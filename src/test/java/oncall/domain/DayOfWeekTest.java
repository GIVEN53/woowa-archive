package oncall.domain;

import static oncall.ui.ErrorMessage.INVALID_DAY_OF_WEEK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DayOfWeekTest {
    @ParameterizedTest
    @ValueSource(strings = {"월", "화", "수", "목", "금", "토", "일"})
    void 올바른_요일일_경우_객체를_생성한다(String dayOfWeekName) {
        // when & then
        DayOfWeek dayOfWeek = DayOfWeek.from(dayOfWeekName);
        assertThat(dayOfWeek.getName()).isEqualTo(dayOfWeekName);
    }

    @Test
    void 올바르지_않은_요일일_경우_예외를_발생시킨다() {
        // given
        String invalidDayOfWeekName = "숨";
        // when & then
        assertThatThrownBy(() -> DayOfWeek.from(invalidDayOfWeekName))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_DAY_OF_WEEK.getMessage());
    }

    @Test
    void 기준_요일을_전달하면_그_요일을_첫_번째로_요일목록을_반환한다() {
        // given
        DayOfWeek start = DayOfWeek.WEDNESDAY;

        // when
        var dayOfWeeks = DayOfWeek.getDayOfWeeksInOrderFrom(start);

        // then
        assertThat(dayOfWeeks.get(0)).isEqualTo(start);
        assertThat(dayOfWeeks.get(6)).isEqualTo(DayOfWeek.TUESDAY);
    }

}