package christmas.dto;

import christmas.domain.menu.Menu;

public record Giveaway(String name, int price) {
    private static final String NO_GIVEAWAY = "없음";
    private static final int NO_PRICE = 0;

    public static Giveaway from(Menu menu) {
        return new Giveaway(menu.getName(), menu.getPrice());
    }

    public static Giveaway noGiveaway() {
        return new Giveaway(NO_GIVEAWAY, NO_PRICE);
    }
}
