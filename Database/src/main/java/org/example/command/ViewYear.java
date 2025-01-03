package org.example.command;

import org.example.Library;

public class ViewYear extends Command{

    @Override
    public String getName() {
        return "vy";
    }

    @Override
    public void execute(Library library) {

    }

    @Override
    public String getDescription() {
        return "View year";
    }
}
