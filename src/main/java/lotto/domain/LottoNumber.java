package lotto.domain;

import static lotto.enums.ErrorMassage.OUT_OF_RANGE_LOTTO_NUMBER;
import static lotto.domain.LottoConfig.LOTTO_MAX_NUMBER;
import static lotto.domain.LottoConfig.LOTTO_MIN_NUMBER;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class LottoNumber {
    private static final Map<Integer, LottoNumber> LOTTO_NUMBER_CACHE = new HashMap<>();

    static {
        IntStream.rangeClosed(LOTTO_MIN_NUMBER.getValue(), LOTTO_MAX_NUMBER.getValue())
                .forEach(i -> LOTTO_NUMBER_CACHE.put(i, new LottoNumber(i)));
    }

    private final int number;

    private LottoNumber(final int number) {
        this.number = number;
    }

    public static LottoNumber from(final int number) {
        validateNumberRange(number);
        return LOTTO_NUMBER_CACHE.get(number);
    }

    private static void validateNumberRange(final int number) {
        if (number < LOTTO_MIN_NUMBER.getValue() || number > LOTTO_MAX_NUMBER.getValue()) {
            throw new IllegalArgumentException(OUT_OF_RANGE_LOTTO_NUMBER.getMassage());
        }
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof LottoNumber lottoNumber) {
            return this.number == lottoNumber.number;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return number;
    }
}
