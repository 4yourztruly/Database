package org.example.menus;

import org.example.Library;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

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
        Optional<String>optionalPassword = Optional.empty();
        Optional<Integer>optionalId = Optional.empty();
        try {
            PreparedStatement loginUser = library.getSaveToDatabase().getConnection().prepareStatement("SELECT * FROM users WHERE username = ?");
            loginUser.setString(1, username);

            try(ResultSet resultSet = loginUser.executeQuery()) {
                if(resultSet.next()) {
                    optionalId = Optional.of(resultSet.getInt("id"));
                    optionalPassword = Optional.of(resultSet.getString("password"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        if(optionalPassword.isEmpty()) {
            System.out.println("Account not found");
            library.getMenuManager().greetMenu();
            return;
        }

        boolean passwordCorrect = BCrypt.checkpw(password, optionalPassword.orElse("password missing"));

        if(passwordCorrect) {
            System.out.println("Logging you in... ");
            library.setUserAccount(username,password, optionalId.orElse(0));
            library.getMenuManager().setMainMenuRunning(true);
            library.getMenuManager().mainMenu();
        } else {
            System.out.println("Wrong credentials!");
            library.getMenuManager().greetMenu();
        }
    }
}
