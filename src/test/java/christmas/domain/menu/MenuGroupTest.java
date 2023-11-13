package christmas.domain.menu;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class MenuGroupTest {
    @Nested
    class 에피타이저_메뉴 {
        @ParameterizedTest
        @EnumSource(value = Menu.class, names = {"MUSHROOM_SOUP", "TAPAS", "CAESAR_SALAD"})
        void true를_반환한다(Menu menu) {
            // when & then
            assertThat(MenuGroup.isAppetizerMenu(menu)).isTrue();
        }

        @ParameterizedTest
        @EnumSource(value = Menu.class, names = {"T_BONE_STEAK", "CHOCO_CAKE", "ZERO_COKE"})
        void false를_반환한다(Menu menu) {
            // when & then
            assertThat(MenuGroup.isAppetizerMenu(menu)).isFalse();
        }
    }

    @Nested
    class 메인_메뉴 {
        @ParameterizedTest
        @EnumSource(value = Menu.class, names = {"T_BONE_STEAK", "BARBECUED_RIB", "SEAFOOD_PASTA", "CHRISTMAS_PASTA"})
        void true를_반환한다(Menu menu) {
            // when & then
            assertThat(MenuGroup.isMainMenu(menu)).isTrue();
        }

        @ParameterizedTest
        @EnumSource(value = Menu.class, names = {"TAPAS", "ICE_CREAM", "RED_WINE"})
        void false를_반환한다(Menu menu) {
            // when & then
            assertThat(MenuGroup.isMainMenu(menu)).isFalse();
        }
    }
}