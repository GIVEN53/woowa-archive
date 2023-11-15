package christmas.ui;

import static christmas.domain.calender.EventDateConfig.MONTH;

public class OutputView {
    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    public void printStartMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printBenefitPreviewMessage(int date) {
        System.out.printf("%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n", MONTH.getValue(), date);
        System.out.println();
    }
}
