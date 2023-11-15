package christmas.util;

import static christmas.ui.ErrorMessage.INVALID_ORDER;
import static christmas.util.Delimiter.COMMA;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ConverterTest {
    @Test
    void 문자열을_숫자로_변환할_수_없을_경우_예외가_발생한다() {
        // given
        String input = "2a";

        // when & then
        assertThatThrownBy(() -> Converter.convertToInt(input))
                .isExactlyInstanceOf(IllegalArgumentException.class);
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

    @Test
    void 문자열을_대시로_나누어_맵_엔트리로_변환한다() {
        // given
        String input = "초코케이크-2";

        // when
        Map.Entry<String, Integer> entry = Converter.convertToMapEntryByDash(input);

        // then
        assertThat(entry.getKey()).isEqualTo("초코케이크");
        assertThat(entry.getValue()).isEqualTo(2);
    }

    @Test
    void 문자열을_대시로_나누어_맵_엔트리로_변환할_수_없을_경우_예외가_발생한다() {
        // given
        String invalidDelimiter = ".";
        String input = "초코케이크" + invalidDelimiter + "2";

        // when & then
        assertThatThrownBy(() -> Converter.convertToMapEntryByDash(input))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_ORDER.getMessage());
    }

    @ParameterizedTest(name = "{0}을 천 단위로 콤마 포함 -> {1}")
    @CsvSource(value = {"1:1", "1000:1,000", "10000:10,000", "100000:100,000", "1000000:1,000,000"}, delimiter = ':')
    void 숫자를_콤마_포함한_문자열로_변환한다(int number, String expected) {
        // when
        String numberWithComma = Converter.convertNumberWithComma(number);

        // then
        assertThat(numberWithComma).isEqualTo(expected);
    }
}