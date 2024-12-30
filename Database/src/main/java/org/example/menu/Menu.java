package org.example.menu;

import org.example.Library;

import java.util.Scanner;

public abstract class Menu {
    protected final Scanner scanner = new Scanner(System.in);

    public abstract void display(Library library);
}
