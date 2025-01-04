package org.example;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Account {
    private final String username;
    private final String password;
    private int balance;
    private int id;
    private Library library;

    public Account(String username, String password, Library library) {
        this.username = username;
        this.password = password;
        this.balance = 0;
        this.library = library;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getBalance() {
        try {
            PreparedStatement preparedStatement = library.getSaveToDatabase().getConnection().prepareStatement("SELECT balance FROM users WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                this.balance = resultSet.getInt("balance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return balance;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setBalance(int balance) {
        this.balance += balance;
    }
}
