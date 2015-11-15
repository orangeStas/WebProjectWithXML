package by.epam.task5.logic.command;

import by.epam.task5.logic.command.impl.LoadInfoCommand;
import by.epam.task5.logic.command.impl.ParseCommand;
import by.epam.task5.logic.command.impl.UnknownCommand;

import java.util.HashMap;

public class CommandHelper {

    private CommandHelper(){}

    private static HashMap<CommandName, ICommand> commands = new HashMap<>();
    static {
        commands.put(CommandName.PARSE_COMMAND, new ParseCommand());
        commands.put(CommandName.UNKNOWN_COMMAND, new UnknownCommand());
        commands.put(CommandName.LOAD_INFO_COMMAND, new LoadInfoCommand());
    }

    public static ICommand getCommand(String commandName) {
        CommandName name = CommandName.valueOf(commandName.toUpperCase());
        if (name != null) {
            return commands.get(name);
        }
        else
            return commands.get(CommandName.UNKNOWN_COMMAND);
    }
}
