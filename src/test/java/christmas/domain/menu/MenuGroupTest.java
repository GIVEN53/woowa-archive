package christmas.domain.menu;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class MenuGroupTest {
    @ParameterizedTest
    @EnumSource(value = Menu.class, names = {"MUSHROOM_SOUP", "TAPAS", "CAESAR_SALAD"})
    void 에피타이저_메뉴이면_true를_반환한다(Menu menu) {
        // when & then
        assertThat(MenuGroup.isAppetizer(menu)).isTrue();
    }

    @ParameterizedTest
    @EnumSource(value = Menu.class, names = {"T_BONE_STEAK", "CHOCO_CAKE", "ZERO_COKE"})
    void 에피타이저_메뉴가_아니면_false를_반환한다(Menu menu) {
        // when & then
        assertThat(MenuGroup.isAppetizer(menu)).isFalse();
    }

    @ParameterizedTest
    @EnumSource(value = Menu.class, names = {"T_BONE_STEAK", "BARBECUED_RIB", "SEAFOOD_PASTA", "CHRISTMAS_PASTA"})
    void 메인_메뉴이면_true를_반환한다(Menu menu) {
        // when & then
        assertThat(MenuGroup.isMain(menu)).isTrue();
    }

    @ParameterizedTest
    @EnumSource(value = Menu.class, names = {"TAPAS", "ICE_CREAM", "RED_WINE"})
    void 메인_메뉴가_아니면_false를_반환한다(Menu menu) {
        // when & then
        assertThat(MenuGroup.isMain(menu)).isFalse();
    }
}