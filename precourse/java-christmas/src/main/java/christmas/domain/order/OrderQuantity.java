package christmas.domain.order;

import static christmas.ui.ErrorMessage.INVALID_ORDER;

record OrderQuantity(int quantity) {
    private static final int MINIMUM_QUANTITY = 1;
    private static final int MAXIMUM_QUANTITY = 50;

    OrderQuantity {
        validateQuantity(quantity);
    }

    private void validateQuantity(int quantity) {
        if (quantity < MINIMUM_QUANTITY || quantity > MAXIMUM_QUANTITY) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }
}
