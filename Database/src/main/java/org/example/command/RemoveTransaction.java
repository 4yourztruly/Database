package org.example.command;

import org.example.Library;

public class RemoveTransaction extends Command{
    @Override
    public String getName() {
        return "rt";
    }

    @Override
    public void execute(Library library) {

    }

    @Override
    public String getDescription() {
        return "Remove transaction";
    }
}
