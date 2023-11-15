package christmas.ui;

import static christmas.domain.calender.EventDateConfig.MONTH;

import christmas.dto.Benefits;
import christmas.util.Converter;
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

    public void printTotalAmountBeforeDiscount(int totalAmountBeforeDiscount) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(Converter.convertNumberWithComma(totalAmountBeforeDiscount) + "원");
        System.out.println();
    }

    public void printGiveaway(String giveaway, int count) {
        System.out.println("<증정 메뉴>");
        System.out.printf("%s %d개\n", giveaway, count);
        System.out.println();
    }

    public void printBenefits(Benefits benefits) {
        System.out.println("<혜택 내역>");
        benefits.benefits().forEach((k, v)-> System.out.printf("%s: -%s원\n", k, Converter.convertNumberWithComma(v)));
        System.out.println();
    }

    public void printTotalDiscountAmount(int totalBenefitAmount) {
        System.out.println("<총혜택 금액>");
        System.out.printf("-%s원%n", Converter.convertNumberWithComma(totalBenefitAmount)));
        System.out.println();
    }

    public void printTotalAmountAfterDiscount(int totalAmountAfterDiscount) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(Converter.convertNumberWithComma(totalAmountAfterDiscount) + "원");
        System.out.println();
    }
}
