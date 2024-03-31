package controller;

import controller.board.BoardController;
import java.util.EnumMap;
import java.util.Map;
import ui.InputView;
import ui.output.BoardOutputView;
import ui.output.GameOutputView;

public class GameController {
    private final InputView inputView;
    private final GameOutputView outputView;
    private final Map<GameCommand, BoardController> commands;

    public GameController(InputView inputView, GameOutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.commands = new EnumMap<>(GameCommand.class);
    }

    private void putCommands() {
        commands.put(GameCommand.START, new BoardController(inputView, new BoardOutputView())); // todo 추상화
        commands.put(GameCommand.END, null); // todo 아무것도 안하도록
    }

    public void run() {
        putCommands();
        outputView.printStartMessage();
        GameCommand command = GameCommand.NONE;
        while (command != GameCommand.END) {
            command = getCommand();
            delegateExecution(command);
        }
    }

    private GameCommand getCommand() {
        try {
            outputView.printCommandMessage();
            String commandName = inputView.readCommandName();
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
