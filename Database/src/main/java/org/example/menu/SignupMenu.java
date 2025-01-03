package org.example.menu;

import org.example.Account;
import org.example.Library;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignupMenu extends Menu{
    @Override
    public void display(Library library) {
        System.out.println("Enter a username: ");
        String username = scanner.nextLine();
        System.out.println("Enter a password: ");
        String password = scanner.nextLine();
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        createAccount(username,hashedPassword,library);
        library.getMenuManager().setCurrentMenu(library.getMenuManager().greetMenu());
        library.getMenuManager().displayCurrentMenu();

    }

    private void createAccount(String username, String password, Library library) {
        Account account = new Account(username,password);
        try {
            PreparedStatement createAccount = library.getSaveToDatabase().getConnection().prepareStatement("INSERT INTO users (username, password, balance) VALUES (?,?,?) ");
            createAccount.setString(1, username);
            createAccount.setString(2, password);
            createAccount.setInt(3,0);
            createAccount.execute();
            createAccount.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        System.out.println("Account created!");
    }
}
