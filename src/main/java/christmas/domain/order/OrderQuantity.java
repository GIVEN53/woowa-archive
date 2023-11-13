package christmas.domain.order;

import static christmas.ui.ErrorMessage.INVALID_ORDER;

class OrderQuantity {
    private static final int MINIMUM_QUANTITY = 1;
    private final int quantity;

    public OrderQuantity(int quantity) {
        validateQuantity(quantity);
        this.quantity = quantity;
    }

    private void validateQuantity(int quantity) {
        if (quantity < MINIMUM_QUANTITY) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }
}
