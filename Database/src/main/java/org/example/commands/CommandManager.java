package org.example.commands;

import org.example.Library;

import java.util.ArrayList;

public class CommandManager {
    private final ArrayList<Command>commands = new ArrayList<>();
    private final Library library;

    public CommandManager(Library library) {
        this.library = library;
        setupCommands();
    }

    public ArrayList<Command> getCommands() {
        return commands;
    }

    private void setupCommands() {
        commands.add(new AddTransaction());
        commands.add(new RemoveTransaction());
        commands.add(new ViewTransaction());
        commands.add(new ViewDay());
        commands.add(new ViewWeek());
        commands.add(new ViewMonth());
        commands.add(new ViewYear());
        commands.add(new Logout());
        commands.add(new Command() {
            @Override
            public String getName() {
                return "help";
            }

            @Override
            public void execute(Library library) {
                for(Command command : commands) {
                    if(command.getName().equalsIgnoreCase("help")) {
                        library.getMenuManager().setCurrentMenu(library.getMenuManager().mainMenu());
                        library.getMenuManager().displayCurrentMenu();
                        return;
                    }
                    System.out.println(command.getName() + " " + command.getDescription());
                }

            }

            @Override
            public String getDescription() {
                return "help";
            }
        });

    }
}
