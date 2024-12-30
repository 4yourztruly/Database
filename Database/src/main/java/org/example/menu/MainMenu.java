package org.example.menu;

import org.example.Library;

public class MainMenu extends Menu{
    @Override
    public void display(Library library) {
        System.out.println("Hello " + library.getUserAccount().getUsername() + "!");

    }
}
