package christmas.domain.calender;

import java.time.DayOfWeek;

public class VisitDate {
    private final int date;
    private final DayOfWeek dayOfWeek;

    public VisitDate(int date, DayOfWeek dayOfWeek) {
        this.date = date;
        this.dayOfWeek = dayOfWeek;
    }
}
