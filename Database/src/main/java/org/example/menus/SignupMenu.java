package org.example.menus;

import org.example.Account;
import org.example.Library;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignupMenu extends Menu {
    @Override
    public void display(Library library) {
        System.out.println("Enter a username: ");
        String username = scanner.nextLine();
        System.out.println("Enter a password: ");
        String password = scanner.nextLine();
        createAccount(username, password, library);
        library.getMenuManager().greetMenu();

    }

    private void createAccount(String username, String password, Library library) {
        boolean exists = usernameExists(username,library);

        if(username.isEmpty() || password.isEmpty()) {
            System.out.println("Empty fields, try again");
            return;
        }

        if(exists) {
            System.out.println("Username already exists, please enter a new username!");
            return;
        }

        String hashPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        try {
            PreparedStatement createAccount = library.getSaveToDatabase().getConnection().prepareStatement("INSERT INTO users (username, password, balance) VALUES (?,?,?) ");
            createAccount.setString(1, username);
            createAccount.setString(2, hashPassword);
            createAccount.setInt(3, 0);
            createAccount.execute();
            createAccount.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        System.out.println("Account created!");
        Account account = new Account(username,hashPassword,library);

    }

    private boolean usernameExists(String username, Library library) {
        boolean usernameExists = false;
        try {
            PreparedStatement preparedStatement = library.getSaveToDatabase().getConnection().prepareStatement("SELECT username FROM users WHERE username = ?");
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                String databaseUsername = resultSet.getString("username");
                if(databaseUsername.equalsIgnoreCase(username)) {
                    usernameExists = true;
                    return usernameExists;
                }
            }

            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usernameExists;
    }
}