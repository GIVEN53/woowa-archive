package oncall.domain;

import static oncall.ui.ErrorMessage.INVALID_NICKNAME_SIZE;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Employee employee) {
            return this.nickname.equals(employee.nickname);
        }
        return false;
    }
}
