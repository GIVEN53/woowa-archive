package christmas.application.restaurant;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import christmas.domain.menu.Menu;
import christmas.domain.order.Order;
import christmas.domain.order.Orders;
import christmas.dto.Giveaway;
import christmas.util.Converter;
import java.util.List;

public class Restaurant {
    private static final int MIN_GIVEAWAY_EVENT_AMOUNT = 120_000;
    private static final Menu GIVEAWAY = Menu.CHAMPAGNE;

    public Orders order(List<String> menus) {
        return menus.stream()
                .map(Converter::convertToMapEntryByDash)
                .map(e -> Order.of(e.getKey(), e.getValue()))
                .collect(collectingAndThen(toList(), Orders::new));
    }

    public Giveaway presentGiveaway(Orders orders) {
        int totalAmountBeforeDiscount = orders.getTotalPrice();

        if (totalAmountBeforeDiscount >= MIN_GIVEAWAY_EVENT_AMOUNT) {
            return Giveaway.from(GIVEAWAY);
        }
        return Giveaway.noGiveaway();
    }
}
