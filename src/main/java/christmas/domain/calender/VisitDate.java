package christmas.domain.calender;

import static christmas.domain.calender.EventDateConfig.END_DAY;
import static christmas.domain.calender.EventDateConfig.MONTH;
import static christmas.domain.calender.EventDateConfig.START_DAY;
import static christmas.domain.calender.EventDateConfig.YEAR;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class VisitDate {
    private static final Map<Integer, VisitDate> DATE_CACHE = new HashMap<>();

    static {
        IntStream.rangeClosed(START_DAY.getValue(), END_DAY.getValue())
                .mapToObj(day -> LocalDate.of(YEAR.getValue(), MONTH.getValue(), day))
                .forEach(date -> {
                    int day = date.getDayOfMonth();
                    DayOfWeek dayOfWeek = date.getDayOfWeek();
                    DATE_CACHE.put(day, new VisitDate(day, dayOfWeek));
                });
    }

    private final int date;
    private final DayOfWeek dayOfWeek;

    private VisitDate(int date, DayOfWeek dayOfWeek) {
        this.date = date;
        this.dayOfWeek = dayOfWeek;
    }

    public static VisitDate from(int date) {
        return DATE_CACHE.get(date);
    }
}
