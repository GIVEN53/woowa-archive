package oncall.domain;

import static oncall.ui.ErrorMessage.INVALID_MONTH;

import java.util.Arrays;
import java.util.List;

public enum Month {
    JANUARY(1, 31, List.of(1)),
    FEBRUARY(2, 28, List.of()),
    MARCH(3, 31, List.of(1)),
    APRIL(4, 30, List.of()),
    MAY(5, 31, List.of(5)),
    JUNE(6, 30, List.of(6)),
    JULY(7, 31, List.of()),
    AUGUST(8, 31, List.of(15)),
    SEPTEMBER(9, 30, List.of()),
    OCTOBER(10, 31, List.of(3, 9)),
    NOVEMBER(11, 30, List.of()),
    DECEMBER(12, 31, List.of(25));

    private static final Month[] MONTHS = values();
    private final int month;
    private final int days;
    private final List<Integer> holidays;

    Month(int month, int days, List<Integer> holidays) {
        this.month = month;
        this.days = days;
        this.holidays = holidays;
    }

    public static Month from(int month) {
        return Arrays.stream(MONTHS)
                .filter(m -> m.getMonth() == month)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_MONTH.getMessage()));
    }

    public int getMonth() {
        return month;
    }

    public int getDays() {
        return days;
    }

    public List<Integer> getHolidays() {
        return holidays;
    }
}
