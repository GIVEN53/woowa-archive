package lotto.domain;

import static lotto.enums.ErrorMassage.INCORRECT_AMOUNT_UNIT;
import static lotto.enums.ErrorMassage.NOT_ENOUGH_AMOUNT;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoAmountTest {
    @Test
    void 구매_금액이_1000원_미만일_경우_예외가_발생한다() {
        // given
        final int amount = 999;

        // when & then
        assertThatThrownBy(() -> new LottoAmount(amount))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage(NOT_ENOUGH_AMOUNT.getMassage());
    }

    @ParameterizedTest
    @ValueSource(ints = {1001, 1234, 2010, 2023, 3400, 4802, 6990, 9999, 10500})
    void 구매_금액이_1000원_단위가_아닐_경우_예외가_발생한다(int amount) {
        // when & then
        assertThatThrownBy(() -> new LottoAmount(amount))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage(INCORRECT_AMOUNT_UNIT.getMassage());
    }

    @ParameterizedTest
    @ValueSource(ints = {1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000, 11000, 12000, 13000, 14000})
    void 올바른_구매_금액일_경우_예외가_발생하지_않는다(int amount) {
        // when & then
        assertDoesNotThrow(() -> new LottoAmount(amount));
    }
}