package oncall.domain;

public class Employee {
    private String nickname;
    private boolean weekdayShift;
    private boolean weekendShift;

    public Employee(String nickname) {
        this.nickname = nickname;
    }
}
