package org.example.command;

import org.example.Library;

public class AddTransaction extends Command{

    @Override
    public String getName() {
        return "at";
    }

    @Override
    public void execute(Library library) {

    }

    @Override
    public String getDescription() {
        return "Add transaction";
    }
}
