package christmas.application.restaurant;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import christmas.application.discount.Event;
import christmas.domain.calender.VisitDate;
import christmas.domain.menu.Menu;
import christmas.domain.order.Order;
import christmas.domain.order.Orders;
import christmas.dto.Benefits;
import christmas.dto.Giveaway;
import christmas.util.Converter;
import java.util.List;
import java.util.Map;

public class Restaurant {
    private static final int MIN_GIVEAWAY_EVENT_AMOUNT = 120_000;
    private static final Menu GIVEAWAY = Menu.CHAMPAGNE;
    private static final int GIVEAWAY_COUNT = 1;
    private static final int MIN_EVENT_AMOUNT = 10_000;
    private final List<Event> events;

    public Restaurant(List<Event> events) {
        this.events = events;
    }

    public Orders order(List<String> menus) {
        return menus.stream()
                .map(Converter::convertToMapEntryByDash)
                .map(e -> Order.of(e.getKey(), e.getValue()))
                .collect(collectingAndThen(toList(), Orders::new));
    }

    public Giveaway presentGiveaway(Orders orders) {
        int totalAmountBeforeDiscount = orders.getTotalPrice();
        if (totalAmountBeforeDiscount >= MIN_GIVEAWAY_EVENT_AMOUNT) {
            return Giveaway.from(GIVEAWAY, GIVEAWAY_COUNT);
        }
        return Giveaway.none();
    }

    public Benefits calculateBenefit(Orders orders, VisitDate visitDate) {
        if (orders.getTotalPrice() < MIN_EVENT_AMOUNT) {
            return Benefits.empty();
        }
        return events.stream()
                .map(e -> Map.entry(e.getName(), e.applyDiscount(orders, visitDate)))
                .filter(e -> e.getValue() > 0)
                .collect(collectingAndThen(
                        toMap(Map.Entry::getKey, Map.Entry::getValue, Integer::sum),
                        Benefits::new)
                );
    }

    public int sumTotalBenefit(Benefits benefits) {
        return benefits.benefits().keySet().stream()
                .mapToInt(benefits::get)
                .sum();
    }
}
