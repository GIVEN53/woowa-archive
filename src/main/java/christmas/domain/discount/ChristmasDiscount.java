package christmas.domain.discount;

import christmas.domain.calender.VisitDate;

public class ChristmasDiscount {
    private static final int CHRISTMAS_DAY = 25;
    private static final int DEFAULT_DISCOUNT_AMOUNT = 1000;
    private static final int DISCOUNT_AMOUNT_PER_DAY = 100;

    public boolean isDiscountDate(VisitDate visitDate) {
        return visitDate.getDate() <= CHRISTMAS_DAY;
    }

    public int calculateDiscountAmount(VisitDate visitDate) {
        return DEFAULT_DISCOUNT_AMOUNT + DISCOUNT_AMOUNT_PER_DAY * (visitDate.getDate() - 1);
    }
}
