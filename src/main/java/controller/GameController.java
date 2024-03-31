package controller;

import java.util.EnumMap;
import java.util.Map;
import view.InputView;
import view.OutputView;

public class GameController {
    private final InputView inputView;
    private final OutputView outputView;
    private final Map<GameCommand, BoardController> commands;

    public GameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.commands = new EnumMap<>(GameCommand.class);
    }

    private void putCommands() {
        commands.put(GameCommand.START, new BoardController(inputView, outputView)); // todo 추상화
        commands.put(GameCommand.END, null); // todo
    }

    public void run() {
        putCommands();
        outputView.printStartingMessage(); // todo 다른 객체로 변경

        GameCommand command = GameCommand.NONE;
        while (command != GameCommand.END) {
            command = getCommand();
            delegateExecution(command);
        }
    }

    private GameCommand getCommand() {
        try {
            String commandName = "start"; // todo inputView.readCommand()
            return GameCommand.findCommand(commandName);
        } catch (Exception e) {
            outputView.printErrorMessage(e);
            return GameCommand.NONE;
        }
    }

    private void delegateExecution(GameCommand command) {
        BoardController boardController = commands.get(command);
        boardController.run();
    }
}
