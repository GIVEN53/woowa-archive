package christmas.ui;

public enum ErrorMessage {
    INVALID_DATE("유효하지 않은 날짜입니다. 다시 입력해 주세요"),
    INVALID_ORDER("유효하지 않은 주문입니다. 다시 입력해 주세요."),
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
