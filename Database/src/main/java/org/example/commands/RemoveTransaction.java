package org.example.commands;

import org.example.Library;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;

public class RemoveTransaction extends Command{
    @Override
    public String getName() {
        return "rt";
    }

    @Override
    public void execute(Library library) {
        int balance = 0;
        boolean exists = false;
        System.out.println("Enter the id of the transaction you want to delete: ");
        int id;

        try {
            id = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Please enter a number and not any letters!");
            scanner.nextLine();
            return;
        }

        try {
            PreparedStatement getBalance = library.getSaveToDatabase().getConnection().prepareStatement("SELECT price FROM transactions WHERE id = ?");
            getBalance.setInt(1,id);
            ResultSet resultSet = getBalance.executeQuery();

            while(resultSet.next()) {
                balance = resultSet.getInt("price");
                exists = true;

            }

            resultSet.close();
            getBalance.close();

            if(!exists) {
                System.out.println("id of item does not exist!");
                return;
            }

            PreparedStatement updateBalance = library.getSaveToDatabase().getConnection().prepareStatement("UPDATE users SET balance = balance - ? WHERE id = ?");
            updateBalance.setInt(1, balance);
            updateBalance.setInt(2, library.getUserAccount().getId());
            updateBalance.execute();
            updateBalance.close();

            PreparedStatement preparedStatement = library.getSaveToDatabase().getConnection().prepareStatement("DELETE FROM transactions WHERE id = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
            preparedStatement.close();

            System.out.println("transaction deleted");
            library.getUserAccount().getBalance();
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }


    }

    @Override
    public String getDescription() {
        return "Remove transaction";
    }
}
