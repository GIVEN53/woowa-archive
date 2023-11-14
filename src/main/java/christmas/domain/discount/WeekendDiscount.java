package christmas.domain.discount;

import christmas.domain.calender.VisitDate;
import christmas.domain.order.Orders;

public final class WeekendDiscount implements Discount {
    private static final int DISCOUNT_AMOUNT_PER_MAIN_MENU = 2023;

    @Override
    public int calculateDiscountAmount(Orders orders, VisitDate visitDate) {
        if (visitDate.isWeekend()) {
            int mainMenuCount = orders.getMainMenuCount();
            return mainMenuCount * DISCOUNT_AMOUNT_PER_MAIN_MENU;
        }
        return NO_DISCOUNT;
    }
}
