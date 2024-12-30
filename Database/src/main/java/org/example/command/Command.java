package org.example.command;

import org.example.Library;

public abstract class Command {
    private final String name;

    public Command(String name) {
        this.name = name;
    }

    public abstract void execute(Library library);

    public abstract void description();

    public String getName() {
        return name;
    }
}
