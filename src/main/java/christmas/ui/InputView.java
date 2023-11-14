package christmas.ui;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public String scanVisitDate() {
        System.out.println("12월 중 식당 에상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!");
        return Console.readLine();
    }
}
