package org.example.command;

import org.example.Library;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Optional;

public class ViewTransaction extends Command{
    @Override
    public String getName() {
        return "vt";
    }

    @Override
    public void execute(Library library) {
        boolean hasResults = false;
        System.out.println("Transactions");
        try {
            PreparedStatement preparedStatement = library.getSaveToDatabase().getConnection().prepareStatement("SELECT * FROM transactions WHERE user_id = ?");
            preparedStatement.setInt(1, library.getUserAccount().getId());

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                hasResults = true;
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                String date = resultSet.getString("date");
                System.out.println("id: " + id + ", " + name + ", " + price + "kr, " + date);
            }
            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        if(!hasResults) {
            System.out.println("No transactions found");
        }

    }

    @Override
    public String getDescription() {
        return "View transaction";
    }
}
