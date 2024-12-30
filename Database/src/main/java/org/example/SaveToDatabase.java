package org.example;

import java.sql.*;

public class SaveToDatabase{
    private Connection connection;

    public void save() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

    }

    public void load() {
        String connectionString = "jdbc:postgresql://localhost/mydb?user=postgres&password=password";

        try {
            connection = DriverManager.getConnection(connectionString);
            setupTables();
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
    }

    private void setupTables() {
        try {
            Statement setupTables = connection.createStatement();
            setupTables.execute("CREATE TABLE IF NOT EXISTS users (id SERIAL PRIMARY KEY, username TEXT, password TEXT, balance INT); CREATE TABLE IF NOT EXISTS transactions (id SERIAL PRIMARY KEY, name TEXT, price INT, date DATE)");
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
    }
}
