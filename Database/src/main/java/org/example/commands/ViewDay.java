package org.example.commands;

import org.example.Library;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;

public class ViewDay extends Command{

    @Override
    public String getName() {
        return "vd";
    }

    @Override
    public void execute(Library library) {
        boolean hasResult = false;
        int balance = 0;
        System.out.println("Enter a day in 1-31 format, ex 1 or 15: ");
        int day = 0;

        try {
            day = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Please enter a number and not any letters");
            scanner.nextLine();
            return;
        }

        if(day <= 0 || day > 31) {
            System.out.println("Enter a number between 1-31 please!");
            return;
        }

        try {
            PreparedStatement preparedStatement = library.getSaveToDatabase().getConnection().prepareStatement("SELECT * FROM transactions WHERE user_id = ? AND EXTRACT(DAY FROM date) = ?");
            preparedStatement.setInt(1, library.getUserAccount().getId());
            preparedStatement.setInt(2, day);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                hasResult = true;
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                String date = resultSet.getString("date");
                int id = resultSet.getInt("id");
                System.out.println("id: " + id + ", " + name + ", " + price + "kr, " + date);
                balance += price;
            }

            if(!resultSet.next() && hasResult) {
                System.out.println("Balance for day " + day + ": " + balance + "kr ");
            }

            if(!hasResult) {
                System.out.println("No transaction for day " + day);
            }

            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
    }

    @Override
    public String getDescription() {
        return "View day";
    }
}
