package org.example.menus;

import org.example.Library;

public class GreetMenu extends Menu{
    @Override
    public void display(Library library) {
        System.out.println("Welcome to Personal finance!");
        System.out.println("Enter login, signup or exit");
        String answer = scanner.nextLine();

        if(answer.equalsIgnoreCase("login")) {
            library.getMenuManager().loginMenu();
        }

        else if(answer.equalsIgnoreCase("signup")) {
            library.getMenuManager().signupMenu();
        }

        else if (answer.equalsIgnoreCase("exit")) {
            System.out.println("Exiting");
            library.getSaveToDatabase().save();
        }

        else {
            System.out.println("Please enter login, signup or exit");
            library.getMenuManager().greetMenu();
        }
    }
}
