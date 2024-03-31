import controller.GameController;
import ui.InputView;
import ui.output.GameOutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        GameOutputView outputView = new GameOutputView();
        GameController gameController = new GameController(inputView, outputView);
        gameController.run();
    }
}
