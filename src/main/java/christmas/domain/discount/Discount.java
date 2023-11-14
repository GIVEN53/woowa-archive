package christmas.domain.discount;

import christmas.domain.calender.VisitDate;
import christmas.domain.order.Orders;

public sealed interface Discount permits ChristmasDiscount, WeekdayDiscount, WeekendDiscount, StarDayDiscount {
    int NO_DISCOUNT = 0;

    int calculateDiscountAmount(Orders orders, VisitDate visitDate);
}
