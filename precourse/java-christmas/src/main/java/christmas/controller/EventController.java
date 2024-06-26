package christmas.controller;

import christmas.application.restaurant.Restaurant;
import christmas.domain.badge.Badge;
import christmas.domain.calender.VisitDate;
import christmas.domain.order.Orders;
import christmas.dto.Benefits;
import christmas.dto.Giveaway;
import christmas.ui.InputView;
import christmas.ui.OutputView;
import christmas.util.Converter;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class EventController {
    private final InputView inputView;
    private final OutputView outputView;
    private final Restaurant restaurant;

    public EventController(InputView inputView, OutputView outputView, Restaurant restaurant) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.restaurant = restaurant;
    }

    public void run() {
        outputView.printStartMessage();
        VisitDate visitDate = getVisitDate();
        Orders orders = getOrders(visitDate);
        int totalAmountBeforeDiscount = getTotalAmountBeforeDiscount(orders);
        int totalBenefitAmount = getTotalBenefitAmount(visitDate, orders, totalAmountBeforeDiscount);
        notifyBadge(totalBenefitAmount);
    }

    private VisitDate getVisitDate() {
        return scanWithRetry(() -> {
            String date = inputView.scanVisitDate();
            return VisitDate.from(Converter.convertToInt(date));
        });
    }

    private Orders getOrders(VisitDate visitDate) {
        return scanWithRetry(() -> {
            String menu = inputView.scanMenuName();
            List<String> menus = Converter.convertToListByComma(menu);
            Orders orders = restaurant.order(menus);
            outputView.printBenefitPreviewMessage(visitDate.getDate());
            outputView.printOrderedMenus(orders.getMenuNameAndCount());
            return orders;
        });
    }

    private <T> T scanWithRetry(final Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private int getTotalAmountBeforeDiscount(Orders orders) {
        int totalAmountBeforeDiscount = orders.getTotalPrice();
        outputView.printTotalAmountBeforeDiscount(totalAmountBeforeDiscount);
        return totalAmountBeforeDiscount;
    }

    private int getTotalBenefitAmount(VisitDate visitDate, Orders orders, int totalAmountBeforeDiscount) {
        Optional<Giveaway> optionalGiveaway = getOptionalGiveaway(orders);
        Benefits benefits = restaurant.calculateBenefit(orders, visitDate);
        int totalBenefitAmount = restaurant.sumTotalBenefit(benefits);
        int totalAmountAfterDiscount = totalAmountBeforeDiscount - totalBenefitAmount;
        totalBenefitAmount = applyGiveawayAndGetTotalBenefitAmount(optionalGiveaway, benefits, totalBenefitAmount);
        printResult(benefits, totalBenefitAmount, totalAmountAfterDiscount);
        return totalBenefitAmount;
    }

    private Optional<Giveaway> getOptionalGiveaway(Orders orders) {
        Optional<Giveaway> optionalGiveaway = restaurant.presentGiveaway(orders);
        outputView.printGiveaway(optionalGiveaway);
        return optionalGiveaway;
    }

    private static int applyGiveawayAndGetTotalBenefitAmount(Optional<Giveaway> optionalGiveaway, Benefits benefits,
                                                             int totalBenefitAmount) {
        if (optionalGiveaway.isPresent()) {
            Giveaway giveaway = optionalGiveaway.get();
            benefits.put(giveaway);
            totalBenefitAmount += giveaway.price();
        }
        return totalBenefitAmount;
    }

    private void printResult(Benefits benefits, int totalBenefitAmount, int totalAmountAfterDiscount) {
        outputView.printBenefits(benefits);
        outputView.printTotalDiscountAmount(totalBenefitAmount);
        outputView.printTotalAmountAfterDiscount(totalAmountAfterDiscount);
    }

    private void notifyBadge(int totalBenefitAmount) {
        Badge badge = Badge.findByBenefitAmount(totalBenefitAmount);
        outputView.printBadge(badge.getName());
    }
}
