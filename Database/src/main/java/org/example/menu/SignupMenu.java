package org.example.menu;

import org.example.Library;

public class SignupMenu extends Menu{
    @Override
    public void display(Library library) {
        System.out.println("Enter a username: ");
        String username = scanner.nextLine();
        System.out.println("Enter a password: ");
        String password = scanner.nextLine();

    }
}
