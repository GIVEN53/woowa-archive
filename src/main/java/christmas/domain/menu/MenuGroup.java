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

import java.util.List;

public enum MenuGroup {
    APPETIZER(List.of(MUSHROOM_SOUP, TAPAS, CAESAR_SALAD)),
    MAIN(List.of(T_BONE_STEAK, BARBECUED_RIB, SEAFOOD_PASTA, CHRISTMAS_PASTA)),
    DESSERT(List.of(CHOCO_CAKE, ICE_CREAM)),
    BEVERAGE(List.of(ZERO_COKE, RED_WINE, CHAMPAGNE));

    private final List<Menu> menus;

    MenuGroup(List<Menu> menus) {
        this.menus = menus;
    }

    public static boolean isAppetizerMenu(Menu menu) {
        return APPETIZER.menus.contains(menu);
    }

    public static boolean isMainMenu(Menu menu) {
        return MAIN.menus.contains(menu);
    }
}
