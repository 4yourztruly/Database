package org.example.command;

import org.example.Library;

import java.util.ArrayList;

public class CommandManager {
    private final ArrayList<Command>commands = new ArrayList<>();
    private final Library library;

    public CommandManager(Library library) {
        this.library = library;
    }

    public void addCommand(Command command) {
        commands.add(command);
    }

    public void searchCommand(String name) {
        for(Command command : commands) {

            String commandName = command.getName();
            if(commandName.equalsIgnoreCase(name)) {
                command.execute(library);
            }
        }
    }
}
