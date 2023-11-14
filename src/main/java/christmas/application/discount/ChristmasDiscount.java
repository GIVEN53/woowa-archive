package christmas.application.discount;

import christmas.domain.calender.VisitDate;
import christmas.domain.order.Orders;

public final class ChristmasDiscount implements Discount {
    private static final int CHRISTMAS_DAY = 25;
    private static final int DEFAULT_DISCOUNT_AMOUNT = 1000;
    private static final int DISCOUNT_AMOUNT_PER_DAY = 100;

    @Override
    public int calculateDiscountAmount(Orders orders, VisitDate visitDate) {
        int date = visitDate.getDate();
        if (date <= CHRISTMAS_DAY) {
            return DEFAULT_DISCOUNT_AMOUNT + DISCOUNT_AMOUNT_PER_DAY * (date - 1);
        }
        return NO_DISCOUNT;
    }
}
