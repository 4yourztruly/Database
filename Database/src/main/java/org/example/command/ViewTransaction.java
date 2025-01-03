package org.example.command;

import org.example.Library;

public class ViewTransaction extends Command{
    @Override
    public String getName() {
        return "vt";
    }

    @Override
    public void execute(Library library) {

    }

    @Override
    public String getDescription() {
        return "View transaction";
    }
}
