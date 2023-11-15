package christmas.application.discount;

import static christmas.application.discount.DiscountName.*;

import christmas.domain.calender.VisitDate;
import christmas.domain.order.Orders;

public final class StarDayEvent implements Event {
    private static final int DEFAULT_DISCOUNT_AMOUNT = 1000;

    @Override
    public int applyDiscount(Orders orders, VisitDate visitDate) {
        if (visitDate.isStarDay()) {
            return DEFAULT_DISCOUNT_AMOUNT;
        }
        return NO_DISCOUNT;
    }

    @Override
    public String getName() {
        return STAR_DAY_EVENT.getName();
    }
}
