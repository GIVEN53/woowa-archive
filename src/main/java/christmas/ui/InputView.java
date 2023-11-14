package christmas.ui;

import camp.nextstep.edu.missionutils.Console;
import christmas.validator.InputValidator;

public class InputView {
    private final InputValidator inputValidator;

    public InputView(InputValidator inputValidator) {
        this.inputValidator = inputValidator;
    }

    public String scanVisitDate() {
        System.out.println("12월 중 식당 에상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!");
        return scanInput();
    }

    public String scanMenuName() {
        System.out.println("주문하실 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        return scanInput();
    }

    private String scanInput() {
        String input = Console.readLine();
        inputValidator.validate(input);
        System.out.println();
        return input;
    }
}
