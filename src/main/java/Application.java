import controller.GateController;
import ui.InputView;
import ui.output.GameOutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        GameOutputView outputView = new GameOutputView();
        GateController gateController = new GateController(inputView, outputView);
        gateController.run();
    }
}
