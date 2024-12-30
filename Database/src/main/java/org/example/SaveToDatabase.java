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

    public Connection getConnection() {
        return connection;
    }

    private void setupTables() {
        try {
            Statement setupTables = connection.createStatement();
            setupTables.execute("CREATE TABLE IF NOT EXISTS users (id SERIAL PRIMARY KEY, username TEXT UNIQUE NOT NULL, password TEXT NOT NULL, balance INT); CREATE TABLE IF NOT EXISTS transactions (id SERIAL PRIMARY KEY, name TEXT NOT NULL, price INT NOT NULL, date DATE NOT NULL, user_id INT NOT NULL, FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE)");
            setupTables.close();

        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
    }
}
