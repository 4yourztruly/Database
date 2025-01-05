package org.example.commands;

import org.example.Library;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.InputMismatchException;

public class AddTransaction extends Command{

    @Override
    public String getName() {
        return "at";
    }

    @Override
    public void execute(Library library) {
        int price;
        System.out.println("Enter name of transaction: ");
        String name = scanner.nextLine();
        System.out.println("Enter price of item: ");

        try{
            price = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Please enter in numbers not letters!");
            scanner.nextLine();
            return;
        }

        System.out.println("Enter date of transaction in format of yyyy-mm-dd: ");
        String date = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try{
            LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Please enter a date in the correct format!");
            return;
        }

        try {
            PreparedStatement preparedStatement = library.getSaveToDatabase().getConnection().prepareStatement("INSERT INTO transactions (name, price, date, user_id) VALUES (?,?,TO_DATE(?, 'YYYY-MM-DD'),?)");
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, price);
            preparedStatement.setString(3, date);
            preparedStatement.setInt(4, library.getUserAccount().getId());
            preparedStatement.execute();
            preparedStatement.close();

            System.out.println("Transaction added");

            library.getUserAccount().getBalance();
            PreparedStatement balanceStatement = library.getSaveToDatabase().getConnection().prepareStatement("UPDATE users SET balance = balance + ? WHERE id = ?");
            balanceStatement.setInt(1, price);
            balanceStatement.setInt(2, library.getUserAccount().getId());
            balanceStatement.execute();
            balanceStatement.close();

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
