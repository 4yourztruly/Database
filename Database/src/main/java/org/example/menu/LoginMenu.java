package org.example.menu;

import org.example.Library;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginMenu extends Menu{
    @Override
    public void display(Library library) {
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        loginAccount(username,password,library);

    }

    private void loginAccount(String username, String password, Library library) {
        String databasePassword = null;
        try {
            PreparedStatement loginUser = library.getSaveToDatabase().getConnection().prepareStatement("SELECT password FROM users WHERE username = ?");
            loginUser.setString(1, username);

            try(ResultSet resultSet = loginUser.executeQuery()) {
                if(resultSet.next()) {
                    databasePassword = resultSet.getString("password");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        if(databasePassword == null) {
            System.out.println("Account not found");
            library.getMenuManager().displayGreetMenu();
        }

        boolean passwordCorrect = BCrypt.checkpw(password, databasePassword);

        if(passwordCorrect) {
            System.out.println("Logging you in... ");
            library.setUserAccount(username,password);
            library.getMenuManager().displayMainMenu();
        } else {
            System.out.println("Wrong credentials!");
            library.getMenuManager().displayGreetMenu();
        }
    }
}
