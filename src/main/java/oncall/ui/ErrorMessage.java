package oncall.ui;

import oncall.util.Delimiter;

public enum ErrorMessage {
    INVALID_MONTH("유효하지 않은 월입니다. 다시 입력해주세요."),
    INVALID_DAY_OF_WEEK("유효하지 않은 요일입니다. 다시 입력해주세요."),
    DUPLICATED_EMPLOYEE("중복된 직원이 존재합니다. 다시 입력해주세요."),
    SEPARATE_BY_COMMA(String.format("구분자(%s)로 구분해주세요.", Delimiter.COMMA.getValue())),
    INVALID_NICKNAME_SIZE(String.format("닉네임은 최대 %d자까지 가능합니다.", 5)),
    LESS_THAN_MINIMUM_EMPLOYEE(String.format("최소 인원(%d명)보다 적습니다. 다시 입력해주세요.", 5)),
    MORE_THAN_MAXIMUM_EMPLOYEE(String.format("최대 인원(%d명)보다 많습니다. 다시 입력해주세요.", 35)),
    IS_BLANK("입력값이 비어있습니다."),
    CONTAIN_SPACE("입력값에 공백이 포함되어 있습니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
