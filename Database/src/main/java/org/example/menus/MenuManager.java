package org.example.menus;

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

    public void greetMenu() {
        greetMenu.display(library);
    }

    public void loginMenu() {
        loginMenu.display(library);
    }

    public void signupMenu() {
        signupMenu.display(library);
    }

    public void mainMenu() {
        mainMenu.display(library);
    }

    public void setMainMenuRunning(boolean running) {
        mainMenu.setRunning(running);
    }
}
