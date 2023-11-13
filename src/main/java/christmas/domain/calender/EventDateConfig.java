package christmas.domain.calender;

public enum EventDateConfig {
    YEAR(2023),
    MONTH(12),
    START_DAY(1),
    END_DAY(31);

    private final int value;

    EventDateConfig(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
