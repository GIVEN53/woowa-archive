package christmas.ui;

import static christmas.domain.calender.EventDateConfig.MONTH;

import christmas.dto.Benefits;
import christmas.dto.Giveaway;
import christmas.util.Converter;
import java.util.Map;
import java.util.Optional;

public class OutputView {
    private static final String NONE = "없음";

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    public void printStartMessage() {
        System.out.printf("안녕하세요! 우테코 식당 %d월 이벤트 플래너입니다.%n", MONTH.getValue());
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

    public void printGiveaway(Optional<Giveaway> optionalGiveaway) {
        System.out.println("<증정 메뉴>");
        System.out.println(getGiveaway(optionalGiveaway));
        System.out.println();
    }

    private String getGiveaway(Optional<Giveaway> optionalGiveaway) {
        if (optionalGiveaway.isPresent()) {
            Giveaway giveaway = optionalGiveaway.get();
            return String.format("%s %d개", giveaway.name(), giveaway.count());
        }
        return NONE;
    }

    public void printBenefits(Benefits benefits) {
        System.out.println("<혜택 내역>");
        if (benefits.isEmpty()) {
            System.out.println(NONE);
            System.out.println();
            return;
        }
        benefits.benefits().forEach((k, v) -> System.out.printf("%s: -%s원\n", k, Converter.convertNumberWithComma(v)));
        System.out.println();
    }

    public void printTotalDiscountAmount(int totalBenefitAmount) {
        System.out.println("<총혜택 금액>");
        System.out.println(getTotalBenefitAmountFormat(totalBenefitAmount));
        System.out.println();
    }

    private String getTotalBenefitAmountFormat(int totalBenefitAmount) {
        if (totalBenefitAmount == 0) {
            return String.format("%s원", Converter.convertNumberWithComma(totalBenefitAmount));
        }
        return String.format("-%s원%n", Converter.convertNumberWithComma(totalBenefitAmount));
    }

    public void printTotalAmountAfterDiscount(int totalAmountAfterDiscount) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(Converter.convertNumberWithComma(totalAmountAfterDiscount) + "원");
        System.out.println();
    }

    public void printBadge(String badgeName) {
        System.out.println("<12월 이벤트 배지>");
        System.out.println(badgeName);
    }
}
