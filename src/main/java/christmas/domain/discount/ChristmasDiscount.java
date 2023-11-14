package christmas.domain.discount;

import christmas.domain.calender.VisitDate;

public class ChristmasDiscount {
    private static final int CHRISTMAS_DAY = 25;

    public boolean isDiscountDate(VisitDate visitDate) {
        return visitDate.getDate() <= CHRISTMAS_DAY;
    }
}
