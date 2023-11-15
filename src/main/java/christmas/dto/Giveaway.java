package christmas.dto;

import christmas.domain.menu.Menu;

public record Giveaway(String name, int price, int count) {
    private static final String NO_GIVEAWAY = "없음";
    private static final int NO_GIVEAWAY_PRICE = 0;
    private static final int NO_GIVEAWAY_COUNT = 0;

    public static Giveaway from(Menu menu, int count) {
        return new Giveaway(menu.getName(), menu.getPrice(), count);
    }

    public static Giveaway none() {
        return new Giveaway(NO_GIVEAWAY, NO_GIVEAWAY_PRICE, NO_GIVEAWAY_COUNT);
    }

    public boolean isNoGiveaway() {
        return this.count == 0;
    }
}
