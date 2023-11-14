package christmas.domain.discount;

import christmas.domain.calender.VisitDate;

public class WeekdayDiscount {
    public boolean isDiscountDate(VisitDate visitDate) {
        return visitDate.isWeekday();
    }
}
