package christmas.application.discount;

import static christmas.application.discount.EventName.*;

import christmas.domain.calender.VisitDate;
import christmas.domain.order.Orders;

public final class WeekdayEvent implements Event {
    private static final int DISCOUNT_AMOUNT_PER_DESSERT_MENU = 2023;

    @Override
    public int applyDiscount(Orders orders, VisitDate visitDate) {
        if (visitDate.isWeekday()) {
            int dessertMenuCount = orders.getDessertMenuCount();
            return dessertMenuCount * DISCOUNT_AMOUNT_PER_DESSERT_MENU;
        }
        return NO_DISCOUNT;
    }

    @Override
    public String getName() {
        return WEEKDAY_EVENT.getName();
    }
}
