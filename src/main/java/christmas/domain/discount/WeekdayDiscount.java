package christmas.domain.discount;

import christmas.domain.calender.VisitDate;
import christmas.domain.order.Orders;

public final class WeekdayDiscount implements Discount {
    private static final int DISCOUNT_AMOUNT_PER_DESSERT_MENU = 2023;

    @Override
    public int calculateDiscountAmount(Orders orders, VisitDate visitDate) {
        if (visitDate.isWeekday()) {
            int dessertMenuCount = orders.getDessertMenuCount();
            return dessertMenuCount * DISCOUNT_AMOUNT_PER_DESSERT_MENU;
        }
        return NO_DISCOUNT;
    }
}
