package christmas.dto;

import christmas.domain.menu.Menu;

public record Giveaway(String name, int price, int count) {
    public static Giveaway from(Menu menu, int count) {
        return new Giveaway(menu.getName(), menu.getPrice(), count);
    }
}
