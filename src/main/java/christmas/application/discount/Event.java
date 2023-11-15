package christmas.application.discount;

import christmas.domain.calender.VisitDate;
import christmas.domain.order.Orders;

public sealed interface Event permits ChristmasEvent, WeekdayEvent, WeekendEvent, StarDayEvent {
    int NO_DISCOUNT = 0;

    int applyDiscount(Orders orders, VisitDate visitDate);

    String getName();
}
