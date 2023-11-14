package christmas.ui;

public enum ErrorMessage {
    INVALID_DATE("유효하지 않은 날짜입니다. 다시 입력해 주세요"),
    INVALID_ORDER("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    ORDERED_ONLY_BEVERAGE_MENU("음료만 주문 시, 주문할 수 없습니다."),
    ORDERED_OVER_MAX_MENU_COUNT("메뉴는 한 번에 최대 %d개까지만 주문할 수 있습니다"),
    IS_BLANK("입력 값이 비어있습니다."),
    CONTAIN_SPACE("입력 값에 공백이 포함되어 있습니다."),
    ;

    private final String message;
    private static final String ERROR = "[ERROR] ";

    ErrorMessage(String message) {
        this.message = ERROR + message;
    }

    public String getMessage() {
        return this.message;
    }
}
