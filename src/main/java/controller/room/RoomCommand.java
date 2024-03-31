package controller.room;

import controller.exception.CommandNotFoundException;
import java.util.Arrays;
import java.util.List;

public enum RoomCommand {
    CREATE,
    DELETE,
    ENTER,
    SHOW,
    END,
    NONE;

    private static final int COMMAND_NAME_INDEX = 0;

    public static RoomCommand findCommand(List<String> rawCommands) {
        String commandName = rawCommands.get(COMMAND_NAME_INDEX);
        return Arrays.stream(values())
                .filter(command -> command.name().equalsIgnoreCase(commandName))
                .findFirst()
                .orElseThrow(() -> new CommandNotFoundException(commandName));
    }
}
