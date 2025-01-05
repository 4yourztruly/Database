package org.example.menus;

import org.example.Library;
import org.example.commands.Command;

public class MainMenu extends Menu{
    public boolean running = false;

    public void setRunning(boolean running) {
        this.running = running;
    }
    @Override
    public void display(Library library) {
        while(running) {
            System.out.println("User: " + library.getUserAccount().getUsername() + " | Balance: " + library.getUserAccount().getBalance() + "kr");
            System.out.println("Enter a command, type help for list of commands.");
            String input = scanner.nextLine();

            for(Command command : library.getCommandManager().getCommands()) {
                if(input.equalsIgnoreCase("l")) {
                    System.out.println("Exiting...");
                    setRunning(false);
                    library.getMenuManager().greetMenu();
                    return;
                }

                if(input.equalsIgnoreCase(command.getName())) {
                    command.execute(library);
                }
            }
        }
    }
}
