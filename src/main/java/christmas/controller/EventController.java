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
        String date = inputView.scanVisitDate();
        VisitDate visitDate = VisitDate.from(Converter.convertToInt(date));

        String menu = inputView.scanMenuName();
        List<String> menus = Converter.convertToListByComma(menu);

        Orders orders = restaurant.order(menus);
        outputView.printBenefitPreviewMessage(visitDate.getDate());
        outputView.printOrderedMenus(orders.getMenuNameAndCount());

        int totalAmountBeforeDiscount = orders.getTotalPrice();
        outputView.printTotalAmountBeforeDiscount(totalAmountBeforeDiscount);
        Optional<Giveaway> optionalGiveaway = restaurant.presentGiveaway(orders);
        outputView.printGiveaway(optionalGiveaway);
        Benefits benefits = restaurant.calculateBenefit(orders, visitDate);
        int totalBenefitAmount = restaurant.sumTotalBenefit(benefits);
        int totalAmountAfterDiscount = totalAmountBeforeDiscount - totalBenefitAmount;
        if (optionalGiveaway.isPresent()) {
            Giveaway giveaway = optionalGiveaway.get();
            benefits.put(giveaway);
            totalBenefitAmount += giveaway.price();
        }
        outputView.printBenefits(benefits);

        outputView.printTotalDiscountAmount(totalBenefitAmount);

        outputView.printTotalAmountAfterDiscount(totalAmountAfterDiscount);

        Badge badge = Badge.findByBenefitAmount(totalBenefitAmount);
        outputView.printBadge(badge.getName());
    }
}
