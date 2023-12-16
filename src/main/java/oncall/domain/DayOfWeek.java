package oncall.domain;

import java.util.List;

public enum DayOfWeek {
    MONDAY("월"),
    TUESDAY("화"),
    WEDNESDAY("수"),
    THURSDAY("목"),
    FRIDAY("금"),
    SATURDAY("토"),
    SUNDAY("일");

    private final String name;
    private static final List<DayOfWeek> WEEKDAY = List.of(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY);
    private static final List<DayOfWeek> WEEKEND = List.of(SATURDAY, SUNDAY);

    DayOfWeek(String name) {
        this.name = name;
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
