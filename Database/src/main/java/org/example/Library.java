package org.example;

import org.example.command.CommandManager;
import org.example.menu.MenuManager;

public class Library {
    private final SaveToDatabase saveToDatabase = new SaveToDatabase();
    private final MenuManager menuManager = new MenuManager(this);
    private final CommandManager commandManager = new CommandManager(this);
    private Account userAccount;

    public Library() {
        saveToDatabase.load();
        menuManager.displayCurrentMenu();
    }

    public SaveToDatabase getSaveToDatabase() {
        return saveToDatabase;
    }

    public MenuManager getMenuManager() {
        return menuManager;
    }

    public void setUserAccount(String username, String password, int id) {
        userAccount = new Account(username,password, this);
        userAccount.setId(id);
    }

    public Account getUserAccount() {
        return userAccount;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

}
