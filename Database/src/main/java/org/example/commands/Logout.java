package org.example.commands;

import org.example.Library;

public class Logout extends Command{

    @Override
    public String getName() {
        return "l";
    }

    @Override
    public void execute(Library library) {

    }

    @Override
    public String getDescription() {
        return "Logout";
    }
}
