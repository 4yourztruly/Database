package org.example;

import java.time.LocalDate;

public class Account {
    private final String username;
    private final String password;
    private int balance;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = 0;
    }

    public void addTransaction() {

    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getBalance() {
        return balance;
    }
}
