package org.example.command;

import org.example.Library;

public class ViewMonth extends Command{

    @Override
    public String getName() {
        return "vm";
    }

    @Override
    public void execute(Library library) {

    }

    @Override
    public String getDescription() {
        return "View month";
    }
}
