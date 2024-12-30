package org.example;

import org.example.command.CommandManager;
import org.example.menu.MenuManager;

public class Library {
    private final SaveToDatabase saveToDatabase = new SaveToDatabase();
    private final MenuManager menuManager = new MenuManager(this);
    private final CommandManager commandManager = new CommandManager(this);

    public Library() {
        saveToDatabase.load();
        menuManager.displayGreetMenu();
    }

    public SaveToDatabase getSaveToDatabase() {
        return saveToDatabase;
    }

    public MenuManager getMenuManager() {
        return menuManager;
    }

}
