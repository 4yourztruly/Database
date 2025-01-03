package org.example.command;

import org.example.Library;

public class ViewDay extends Command{

    @Override
    public String getName() {
        return "vd";
    }

    @Override
    public void execute(Library library) {

    }

    @Override
    public String getDescription() {
        return "View day";
    }
}
