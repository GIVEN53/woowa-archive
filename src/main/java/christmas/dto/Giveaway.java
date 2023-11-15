package christmas.dto;

import christmas.domain.menu.Menu;

public record Giveaway(String name) {
    private static final String NO_GIVEAWAY = "없음";
    public static Giveaway from(Menu menu) {
        return new Giveaway(menu.getName());
    }

    public static Giveaway noGiveaway() {
        return new Giveaway(NO_GIVEAWAY);
    }
}
