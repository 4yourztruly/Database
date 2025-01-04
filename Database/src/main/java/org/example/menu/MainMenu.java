package org.example.menu;

import org.example.Library;
import org.example.command.Command;

public class MainMenu extends Menu{
    @Override
    public void display(Library library) {
            System.out.println("User: " + library.getUserAccount().getUsername() + " | Balance: " + library.getUserAccount().getBalance());
            System.out.println("Enter a command, type help for list of commands.");
            String input = scanner.nextLine();

            for(Command command : library.getCommandManager().getCommands()) {
                if(input.equalsIgnoreCase("l")) {
                    System.out.println("Exiting...");
                    library.getMenuManager().setCurrentMenu(library.getMenuManager().greetMenu());
                    library.getMenuManager().displayCurrentMenu();
                    return;
                }

                if(input.equalsIgnoreCase(command.getName())) {
                    command.execute(library);
                }
            }
    }
}
