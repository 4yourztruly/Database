package org.example.menu;

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
        try {
            PreparedStatement loginUser = library.getSaveToDatabase().getConnection().prepareStatement("SELECT password FROM users WHERE username = ?");
            loginUser.setString(1, username);

            try(ResultSet resultSet = loginUser.executeQuery()) {
                if(resultSet.next()) {
                    optionalPassword = Optional.of(resultSet.getString("password"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        if(optionalPassword.isEmpty()) {
            System.out.println("Account not found");
            library.getMenuManager().setCurrentMenu(library.getMenuManager().greetMenu());
            library.getMenuManager().displayCurrentMenu();
            return;
        }

        boolean passwordCorrect = BCrypt.checkpw(password, optionalPassword.orElse("password missing"));

        if(passwordCorrect) {
            System.out.println("Logging you in... ");
            library.setUserAccount(username,password);
            library.getMenuManager().setCurrentMenu(library.getMenuManager().mainMenu());
            library.getMenuManager().displayCurrentMenu();
        } else {
            System.out.println("Wrong credentials!");
            library.getMenuManager().setCurrentMenu(library.getMenuManager().greetMenu());
            library.getMenuManager().displayCurrentMenu();
        }
    }
}
