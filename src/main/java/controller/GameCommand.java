package controller;

import controller.exception.CommandNotFoundException;
import java.util.Arrays;

public enum GameCommand {
    START,
    END,
    NONE;

    public static GameCommand findCommand(String commandName) {
        return Arrays.stream(values())
                .filter(command -> command.name().equalsIgnoreCase(commandName))
                .findFirst()
                .orElseThrow(() -> new CommandNotFoundException(commandName));
    }
}
