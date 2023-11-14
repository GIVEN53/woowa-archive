package christmas.domain.badge;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BadgeTest {
    static Stream<Arguments> provideBadgeAndBenefitAmount() {
        return Stream.of(
            Arguments.of(Badge.NONE, 0),
            Arguments.of(Badge.NONE, 4_999),
            Arguments.of(Badge.STAR, 5_000),
            Arguments.of(Badge.STAR, 9_999),
            Arguments.of(Badge.TREE, 10_000),
            Arguments.of(Badge.TREE, 19_999),
            Arguments.of(Badge.SANTA, 20_000),
            Arguments.of(Badge.SANTA, 100_000)
        );
    }

    @ParameterizedTest
    @MethodSource("provideBadgeAndBenefitAmount")
    void 혜택_금액에_따라_배지를_반환한다(Badge expected, int benefitAmount) {
        // when
        Badge badge = Badge.findByBenefitAmount(benefitAmount);

        // then
        assertThat(badge).isEqualTo(expected);
    }
}