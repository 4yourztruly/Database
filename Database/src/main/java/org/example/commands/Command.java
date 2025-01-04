package org.example.commands;

import org.example.Library;

import java.util.Scanner;

public abstract class Command {
    protected Scanner scanner = new Scanner(System.in);

    public abstract String getName();

    public abstract void execute(Library library);

    public abstract String getDescription();
}
