package oncall.domain;

import static oncall.ui.ErrorMessage.*;

import oncall.ui.ErrorMessage;

public class Employee {
    private String nickname;
    private boolean weekdayShift;
    private boolean weekendShift;

    public Employee(String nickname) {
        validateNicknameSize(nickname);
        this.nickname = nickname;
    }

    private void validateNicknameSize(String nickname) {
        if (nickname.length() > 5) {
            throw new IllegalArgumentException(INVALID_NICKNAME_SIZE.getMessage());
        }
    }
}
