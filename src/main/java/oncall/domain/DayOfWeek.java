package oncall.domain;

import static oncall.ui.ErrorMessage.INVALID_DAY_OF_WEEK;

import java.util.Arrays;
import java.util.List;

public enum DayOfWeek {
    MONDAY("월"),
    TUESDAY("화"),
    WEDNESDAY("수"),
    THURSDAY("목"),
    FRIDAY("금"),
    SATURDAY("토"),
    SUNDAY("일");

    private static final List<DayOfWeek> WEEKDAY = List.of(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY);
    private static final List<DayOfWeek> WEEKEND = List.of(SATURDAY, SUNDAY);
    private final String name;

    DayOfWeek(String name) {
        this.name = name;
    }

    public static DayOfWeek from(String name) {
        return Arrays.stream(values())
                .filter(dayOfWeek -> dayOfWeek.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_DAY_OF_WEEK.getMessage()));
    }

    public String getName() {
        return name;
    }

    public boolean isWeekday() {
        return WEEKDAY.contains(this);
    }

    public boolean isWeekend() {
        return WEEKEND.contains(this);
    }
}
