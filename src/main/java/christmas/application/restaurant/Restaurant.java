package christmas.application.restaurant;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import christmas.domain.order.Order;
import christmas.domain.order.Orders;
import christmas.util.Converter;
import java.util.List;

public class Restaurant {
    public Orders order(List<String> menus) {
        return menus.stream()
                .map(Converter::convertToMapEntryByDash)
                .map(e -> Order.of(e.getKey(), e.getValue()))
                .collect(collectingAndThen(toList(), Orders::new));
    }
}
