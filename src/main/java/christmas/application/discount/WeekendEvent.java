package christmas.application.discount;

import static christmas.application.discount.EventName.WEEKEND_EVENT;

import christmas.domain.calender.VisitDate;
import christmas.domain.order.Orders;

public final class WeekendEvent implements Event {
    private static final int DISCOUNT_AMOUNT_PER_MAIN_MENU = 2023;

    @Override
    public int applyDiscount(Orders orders, VisitDate visitDate) {
        if (visitDate.isWeekend()) {
            int mainMenuCount = orders.getMainMenuCount();
            return mainMenuCount * DISCOUNT_AMOUNT_PER_MAIN_MENU;
        }
        return NO_DISCOUNT;
    }

    @Override
    public String getName() {
        return WEEKEND_EVENT.getName();
    }
}
