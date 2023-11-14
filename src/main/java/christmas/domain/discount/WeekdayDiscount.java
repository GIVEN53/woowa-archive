package christmas.domain.discount;

import christmas.domain.calender.VisitDate;
import christmas.domain.order.Orders;

public class WeekdayDiscount {
    private static final int DISCOUNT_AMOUNT_PER_DESSERT_MENU = 2023;
    public boolean isDiscountDate(VisitDate visitDate) {
        return visitDate.isWeekday();
    }

    public int calculateDiscountAmount(Orders orders) {
        int dessertMenuCount = orders.getDessertMenuCount();
        return dessertMenuCount * DISCOUNT_AMOUNT_PER_DESSERT_MENU;
    }
}
