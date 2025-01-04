package org.example.command;

import org.example.Library;

import javax.print.MultiDocPrintService;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class AddTransaction extends Command{

    @Override
    public String getName() {
        return "at";
    }

    @Override
    public void execute(Library library) {
        System.out.println("Enter name of transaction: ");
        String name = scanner.nextLine();
        System.out.println("Enter price of item: ");
        int price = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter date of transaction in format of yyyy-mm-dd: ");
        String date = scanner.nextLine();

        try {
            PreparedStatement preparedStatement = library.getSaveToDatabase().getConnection().prepareStatement("INSERT INTO transactions (name, price, date, user_id) VALUES (?,?,TO_DATE(?, 'YYYY-MM-DD'),?)");
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, price);
            preparedStatement.setString(3, date);
            preparedStatement.setInt(4, library.getUserAccount().getId());
            preparedStatement.execute();
            preparedStatement.close();
            System.out.println("Transaction added");
            library.getMenuManager().setCurrentMenu(library.getMenuManager().mainMenu());
            library.getMenuManager().displayCurrentMenu();

        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }


    }

    @Override
    public String getDescription() {
        return "Add transaction";
    }
}
