package christmas.domain.order;

import static christmas.ui.ErrorMessage.INVALID_ORDER;

record OrderQuantity(int quantity) {
    private static final int MINIMUM_QUANTITY = 1;

    OrderQuantity {
        validateQuantity(quantity);
    }

    private void validateQuantity(int quantity) {
        if (quantity < MINIMUM_QUANTITY) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }
}
