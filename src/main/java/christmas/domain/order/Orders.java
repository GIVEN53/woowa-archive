package christmas.domain.order;

import static christmas.ui.ErrorMessage.INVALID_ORDER;
import static christmas.ui.ErrorMessage.ORDERED_ONLY_BEVERAGE_MENU;
import static christmas.ui.ErrorMessage.ORDERED_OVER_MAX_MENU_COUNT;

import java.util.List;

public class Orders {
    private static final int MAX_MENU_COUNT = 20;
    private final List<Order> orders;

    public Orders(List<Order> orders) {
        validateDuplicateMenu(orders);
        validateOnlyBeverageMenu(orders);
        validateMenuTotalCount(orders);
        this.orders = orders;
    }

    private void validateDuplicateMenu(List<Order> orders) {
        int distinctCount = (int) orders.stream()
                .map(Order::getMenuName)
                .distinct()
                .count();
        if (distinctCount != orders.size()) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }

    private void validateOnlyBeverageMenu(List<Order> orders) {
        boolean onlyBeverageMenu = orders.stream()
                .allMatch(Order::hasBeverageMenu);

        if (onlyBeverageMenu) {
            throw new IllegalArgumentException(ORDERED_ONLY_BEVERAGE_MENU.getMessage());
        }
    }

    private void validateMenuTotalCount(List<Order> orders) {
        int totalCount = orders.stream()
                .mapToInt(Order::getOrderQuantity)
                .sum();
        if (totalCount > MAX_MENU_COUNT) {
            throw new IllegalArgumentException(String.format(ORDERED_OVER_MAX_MENU_COUNT.getMessage(), MAX_MENU_COUNT));
        }
    }

    public int getTotalPrice() {
        return orders.stream()
                .mapToInt(o -> o.getMenuPrice() * o.getOrderQuantity())
                .sum();
    }

    public int getDessertMenuCount() {
        return orders.stream()
                .filter(Order::hasDessertMenu)
                .mapToInt(Order::getOrderQuantity)
                .sum();
    }

    public int getMainMenuCount() {
        return orders.stream()
                .filter(Order::hasMainMenu)
                .mapToInt(Order::getOrderQuantity)
                .sum();
    }
}
