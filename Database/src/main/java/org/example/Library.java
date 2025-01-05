package org.example;

import org.example.commands.CommandManager;
import org.example.menus.MenuManager;

public class Library {
    private final SaveToDatabase saveToDatabase = new SaveToDatabase();
    private final MenuManager menuManager = new MenuManager(this);
    private final CommandManager commandManager = new CommandManager();
    private Account userAccount;

    public Library() {
        saveToDatabase.load();
        menuManager.greetMenu();
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
