package christmas.application.discount;

import christmas.domain.calender.VisitDate;
import christmas.domain.order.Orders;

public final class StarDayDiscount implements Discount{
    private static final int DEFAULT_DISCOUNT_AMOUNT = 1000;

    @Override
    public int calculateDiscountAmount(Orders orders, VisitDate visitDate) {
        if (visitDate.isStarDay()) {
            return DEFAULT_DISCOUNT_AMOUNT;
        }
        return NO_DISCOUNT;
    }
}
