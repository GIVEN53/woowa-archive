package controller;

import controller.board.BoardController;
import java.util.EnumMap;
import java.util.Map;
import ui.InputView;
import ui.output.BoardOutputView;
import ui.output.GameOutputView;

public class GateController {
    private final InputView inputView;
    private final GameOutputView outputView;
    private final Map<GateCommand, GameController> commands;

    public GateController(InputView inputView, GameOutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.commands = new EnumMap<>(GateCommand.class);
    }

    private void putCommands() {
        commands.put(GateCommand.START, new BoardController(inputView, new BoardOutputView())); // todo 추상화
    }

    public void run() {
        putCommands();
        outputView.printStartMessage();
        GateCommand command;
        while ((command = getCommand()) != GateCommand.END) {
            delegateExecution(command);
        }
    }

    private GateCommand getCommand() {
        try {
            outputView.printCommandMessage();
            return GateCommand.findCommand(inputView.readCommandName());
        } catch (Exception e) {
            outputView.printErrorMessage(e);
            return getCommand();
        }
    }

    private void delegateExecution(GateCommand command) {
        GameController gameController = commands.get(command);
        gameController.execute();
    }
}
