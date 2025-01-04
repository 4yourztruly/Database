package org.example.command;

import org.example.Library;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewWeek extends Command{

    @Override
    public String getName() {
        return "vw";
    }

    @Override
    public void execute(Library library) {
        boolean hasResult = false;
        int balance = 0;
        System.out.println("Enter a week in ww format, ex 45: ");
        int week = scanner.nextInt();
        scanner.nextLine();

        try {
            PreparedStatement preparedStatement = library.getSaveToDatabase().getConnection().prepareStatement("SELECT * FROM transactions WHERE user_id = ? AND EXTRACT(WEEK FROM date) = ?");
            preparedStatement.setInt(1, library.getUserAccount().getId());
            preparedStatement.setInt(2, week);
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
                System.out.println("Balance for week " + week + ": " + balance + "kr ");
            }

            if(!hasResult) {
                System.out.println("No transaction for this week");
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
        return "View week";
    }
}
