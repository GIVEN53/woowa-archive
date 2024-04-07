package oncall.domain;

public record ShiftDate(Month month, int day, DayOfWeek dayOfWeek) {

    public boolean isWeekday() {
        return dayOfWeek.isWeekday();
    }

    public boolean isWeekend() {
        return dayOfWeek.isWeekend();
    }

    public boolean isHoliday() {
        return month.isHoliday(day);
    }
}
