package christmas.domain.order;

import static christmas.ui.ErrorMessage.INVALID_ORDER;

import java.util.List;

public class Orders {
    private final List<Order> orders;

    public Orders(List<Order> orders) {
        validateDuplicateMenu(orders);
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
}
