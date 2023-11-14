package christmas.util;

import static christmas.ui.ErrorMessage.INVALID_NUMBER;
import static christmas.ui.ErrorMessage.INVALID_ORDER;
import static christmas.util.Delimiter.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
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

    @Test
    void 문자열을_콤마로_나누어_리스트로_변환한다() {
        // given
        String input = "초코케이크-2" + COMMA.getValue() + "바비큐립-1";

        // when
        List<String> orders = Converter.convertToListByComma(input);

        // then
        assertThat(orders).containsExactly("초코케이크-2", "바비큐립-1");
    }

    @Test
    void 문자열을_콤마로_나누어_리스트로_변환할_수_없을_경우_예외가_발생한다() {
        // given
        String invalidDelimiter = ".";
        String input = "초코케이크-2" + invalidDelimiter + "바비큐립-1";

        // when & then
        assertThatThrownBy(() -> Converter.convertToListByComma(input))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_ORDER.getMessage());
    }
}