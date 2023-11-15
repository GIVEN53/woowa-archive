package christmas;

import christmas.application.discount.ChristmasEvent;
import christmas.application.discount.Event;
import christmas.application.discount.StarDayEvent;
import christmas.application.discount.WeekdayEvent;
import christmas.application.discount.WeekendEvent;
import christmas.application.restaurant.Restaurant;
import christmas.controller.EventController;
import christmas.ui.InputView;
import christmas.ui.OutputView;
import christmas.validator.InputValidator;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        final InputView inputView = new InputView(new InputValidator());
        final OutputView outputView = new OutputView();
        final List<Event> events = List.of(
                new ChristmasEvent(),
                new WeekdayEvent(),
                new WeekendEvent(),
                new StarDayEvent()
        );
        final Restaurant restaurant = new Restaurant(events);
        final EventController eventController = new EventController(inputView, outputView, restaurant);
        eventController.run();
    }
}
