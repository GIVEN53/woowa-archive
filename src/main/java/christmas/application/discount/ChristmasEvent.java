package christmas.application.discount;

import static christmas.application.discount.DiscountName.*;

import christmas.domain.calender.VisitDate;
import christmas.domain.order.Orders;

public final class ChristmasEvent implements Event {
    private static final int CHRISTMAS_DAY = 25;
    private static final int DEFAULT_DISCOUNT_AMOUNT = 1000;
    private static final int DISCOUNT_AMOUNT_PER_DAY = 100;

    @Override
    public int applyDiscount(Orders orders, VisitDate visitDate) {
        int date = visitDate.getDate();
        if (date <= CHRISTMAS_DAY) {
            return DEFAULT_DISCOUNT_AMOUNT + DISCOUNT_AMOUNT_PER_DAY * (date - 1);
        }
        return NO_DISCOUNT;
    }

    @Override
    public String getName() {
        return CHRISTMAS_EVENT.getName();
    }
}
