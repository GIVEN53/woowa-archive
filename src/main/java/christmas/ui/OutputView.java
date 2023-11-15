package christmas.ui;

import static christmas.domain.calender.EventDateConfig.MONTH;

import java.util.Map;

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

    public void printOrderedMenus(Map<String, Integer> orderedMenus) {
        System.out.println("<주문 메뉴>");
        orderedMenus.forEach((k, v) -> System.out.printf("%s %d개\n", k, v));
        System.out.println();
    }

    public void printTotalAmountBeforeDiscount(String totalAmountBeforeDiscount) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(totalAmountBeforeDiscount + "원");
        System.out.println();
    }
}
