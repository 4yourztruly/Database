package org.example.menus;

import org.example.Library;

public class MenuManager {
    private final Library library;
    private final GreetMenu greetMenu = new GreetMenu();
    private final LoginMenu loginMenu = new LoginMenu();
    private final SignupMenu signupMenu = new SignupMenu();
    private final MainMenu mainMenu = new MainMenu();
    private Menu currentMenu = greetMenu;
    private boolean running = true;

    public MenuManager(Library library) {
        this.library = library;
    }

    public Menu greetMenu() {
        return greetMenu;
    }

    public Menu loginMenu() {
        return loginMenu;
    }

    public Menu signupMenu() {
        return signupMenu;
    }

    public Menu mainMenu() {
        return mainMenu;
    }

    public void setCurrentMenu(Menu menu) {
        currentMenu = menu;
    }

    public void setNullMenu() {
        currentMenu = null;
    }

    public void displayCurrentMenu() {
        while(running) {
            if(currentMenu == null) {
                running = false;
                return;
            }
            currentMenu.display(library);
        }

    }
}
