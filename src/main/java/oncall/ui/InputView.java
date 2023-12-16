package oncall.ui;

import camp.nextstep.edu.missionutils.Console;
import oncall.validator.InputValidator;

public class InputView {
    private final InputValidator inputValidator;

    public InputView(InputValidator inputValidator) {
        this.inputValidator = inputValidator;
    }

    public String scanMonthAndDayOfWeek() {
        System.out.print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ");
        String input = Console.readLine();
        inputValidator.validate(input);
        return input;
    }

    public String scanEmployeeNickNameForWeekdayShift() {
        System.out.print("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
        String input = Console.readLine();
        inputValidator.validate(input);
        return input;
    }
}
