package org.example.command;

import org.example.Library;

public class ViewWeek extends Command{

    @Override
    public String getName() {
        return "vw";
    }

    @Override
    public void execute(Library library) {

    }

    @Override
    public String getDescription() {
        return "View week";
    }
}
