package org.example.menu;

import org.example.Library;

public class MenuManager {
    private final Library library;
    private final GreetMenu greetMenu = new GreetMenu();
    private final LoginMenu loginMenu = new LoginMenu();
    private final SignupMenu signupMenu = new SignupMenu();
    private final MainMenu mainMenu = new MainMenu();

    public MenuManager(Library library) {
        this.library = library;
    }

    public void displayGreetMenu() {
        greetMenu.display(library);
    }

    public void displayLoginMenu() {
        loginMenu.display(library);
    }

    public void displaySignupMenu() {
        signupMenu.display(library);
    }

    public void displayMainMenu() {
        mainMenu.display(library);
    }

}
