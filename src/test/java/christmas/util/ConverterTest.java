package christmas.util;

import static christmas.ui.ErrorMessage.INVALID_NUMBER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class ConverterTest {
    @Test
    void 문자열을_숫자로_변환할_수_없을_경우_예외가_발생한다() {
        // given
        String input = "2a";

        // when & then
        assertThatThrownBy(() -> Converter.convertToInt(input))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_NUMBER.getMessage());
    }

    @Test
    void 문자열을_숫자로_변환한다() {
        // given
        String input = "11";

        // when
        int number = Converter.convertToInt(input);

        // then
        assertThat(number).isEqualTo(11);
    }
}