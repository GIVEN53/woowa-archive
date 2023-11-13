package christmas.domain.order;

import static christmas.ui.ErrorMessage.INVALID_ORDER;

import java.util.List;

public class Orders {
    private final List<Order> orders;

    public Orders(List<Order> orders) {
        validateDuplicateMenu(orders);
        validateOnlyBeverageMenu(orders);
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
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }
}
