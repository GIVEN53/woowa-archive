package oncall;

import oncall.application.ShiftAssignmentService;
import oncall.controller.ShiftController;
import oncall.ui.InputView;
import oncall.ui.OutputView;
import oncall.validator.InputValidator;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView(new InputValidator());
        OutputView outputView = new OutputView();
        ShiftController shiftController = new ShiftController(inputView, outputView, new ShiftAssignmentService());
        shiftController.run();
    }
}
