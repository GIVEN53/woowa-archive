package christmas.domain.menu;

import static christmas.domain.menu.Menu.BARBECUED_RIB;
import static christmas.domain.menu.Menu.CAESAR_SALAD;
import static christmas.domain.menu.Menu.CHAMPAGNE;
import static christmas.domain.menu.Menu.CHOCO_CAKE;
import static christmas.domain.menu.Menu.CHRISTMAS_PASTA;
import static christmas.domain.menu.Menu.ICE_CREAM;
import static christmas.domain.menu.Menu.MUSHROOM_SOUP;
import static christmas.domain.menu.Menu.RED_WINE;
import static christmas.domain.menu.Menu.SEAFOOD_PASTA;
import static christmas.domain.menu.Menu.TAPAS;
import static christmas.domain.menu.Menu.T_BONE_STEAK;
import static christmas.domain.menu.Menu.ZERO_COKE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MenuTest {
    static Stream<Arguments> provideMenu() {
        return Stream.of(
                Arguments.of("양송이수프", MUSHROOM_SOUP),
                Arguments.of("타파스", TAPAS),
                Arguments.of("시저샐러드", CAESAR_SALAD),
                Arguments.of("티본스테이크", T_BONE_STEAK),
                Arguments.of("바비큐립", BARBECUED_RIB),
                Arguments.of("해산물파스타", SEAFOOD_PASTA),
                Arguments.of("크리스마스파스타", CHRISTMAS_PASTA),
                Arguments.of("초코케이크", CHOCO_CAKE),
                Arguments.of("아이스크림", ICE_CREAM),
                Arguments.of("제로콜라", ZERO_COKE),
                Arguments.of("레드와인", RED_WINE),
                Arguments.of("샴페인", CHAMPAGNE)
        );
    }

    @ParameterizedTest(name = "메뉴명: {0} - 메뉴: {1}")
    @MethodSource("provideMenu")
    void 메뉴명에_해당하는_메뉴를_반환한다(String name, Menu menu) {
        // when
        Menu actual = Menu.findByName(name);

        // then
        assertThat(actual).isEqualTo(menu);
    }

    @Test
    void 메뉴에_없는_메뉴명으로_찾을_경우_예외를_발생한다() {
        // given
        String invalidName = "치킨";

        // when & then
        assertThatThrownBy(() -> Menu.findByName(invalidName))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }
}