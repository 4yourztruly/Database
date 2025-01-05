package org.example.commands;

import org.example.Library;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;

public class ViewYear extends Command{

    @Override
    public String getName() {
        return "vy";
    }

    @Override
    public void execute(Library library) {
        boolean hasResult = false;
        int balance = 0;
        System.out.println("Enter a year in yyyy format: ");
        int year = 0;

        try {
            year = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Please enter numbers and not any letters!");
            scanner.nextLine();
            return;
        }

        if(year <= 0 || year > 9999) {
            System.out.println("Please enter a more appropriate year!");
            return;
        }


        try {
            PreparedStatement preparedStatement = library.getSaveToDatabase().getConnection().prepareStatement("SELECT * FROM transactions WHERE user_id = ? AND EXTRACT(YEAR FROM date) = ?");
            preparedStatement.setInt(2, year);
            preparedStatement.setInt(1, library.getUserAccount().getId());
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
                System.out.println("Balance for the year " + year + ": " + balance + "kr ");
            }

            if(!hasResult) {
                System.out.println("No transaction for this year");
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
        return "View year";
    }
}
