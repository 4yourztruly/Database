package org.example.menu;

import org.example.Library;

public class GreetMenu extends Menu{
    @Override
    public void display(Library library) {
        System.out.println("Welcome to Personal finance!");
        System.out.println("Enter login, signup or exit");
        String answer = scanner.nextLine();

        if(answer.equalsIgnoreCase("login")) {
            library.getMenuManager().setCurrentMenu(library.getMenuManager().loginMenu());
            library.getMenuManager().displayCurrentMenu();
        }

        else if(answer.equalsIgnoreCase("signup")) {
            library.getMenuManager().setCurrentMenu(library.getMenuManager().signupMenu());
            library.getMenuManager().displayCurrentMenu();
        }

        else if (answer.equalsIgnoreCase("exit")) {
            System.out.println("Exiting");
            library.getMenuManager().setNullMenu();
            library.getMenuManager().displayCurrentMenu();
            library.getSaveToDatabase().save();
        }

        else {
            System.out.println("Please enter login, signup or exit");
            library.getMenuManager().setCurrentMenu(library.getMenuManager().greetMenu());
            library.getMenuManager().displayCurrentMenu();
        }
    }
}
