package christmas.application.discount;

public enum EventName {
    CHRISTMAS_EVENT("크리스마스 디데이 할인"),
    WEEKDAY_EVENT("평일 할인"),
    WEEKEND_EVENT("주말 할인"),
    STAR_DAY_EVENT("특별 할인"),
    GIVEAWAY_EVENT("증정 이벤트");

    private final String name;

    EventName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
