package christmas.domain.calender;

import static christmas.domain.calender.EventDateConfig.END_DAY;
import static christmas.domain.calender.EventDateConfig.MONTH;
import static christmas.domain.calender.EventDateConfig.START_DAY;
import static christmas.domain.calender.EventDateConfig.YEAR;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class VisitDate {
    private static final List<DayOfWeek> WEEKDAY = List.of(SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY);
    private static final List<DayOfWeek> WEEKEND = List.of(FRIDAY, SATURDAY);
    private static final List<Integer> STAR_DAYS = List.of(3, 10, 17, 24, 25, 31);

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

    public boolean isWeekday() {
        return WEEKDAY.contains(dayOfWeek);
    }

    public boolean isWeekend() {
        return WEEKEND.contains(dayOfWeek);
    }

    public boolean isStarDay() {
        return STAR_DAYS.contains(date);
    }
}
