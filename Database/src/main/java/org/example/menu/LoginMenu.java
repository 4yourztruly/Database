package org.example.menu;

import org.example.Library;

public class LoginMenu extends Menu{
    @Override
    public void display(Library library) {
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();

    }
}
