package controllers;

import models.DbConnection;
import models.Task;
import models.UpdateList;

import java.sql.*;

public class DbController {
    private static PreparedStatement statement = null;

    public static void addUnknownTask(Connection connection, String item) {
        try {

            statement = connection.prepareStatement(
                    "SELECT COUNT(item) FROM new_items WHERE item= ?");

            statement.setString(1, item);

            ResultSet resultSet = statement.executeQuery();


            while (resultSet.next()) {
                if (resultSet.getInt(1) == 0) {
                    statement = connection.prepareStatement(
                            "INSERT INTO new_items (item) VALUES (?)");

                    statement.setString(1, item);

                    statement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static String findItem(Connection connection, Task task) {
        String category = "";

        try {

            statement = connection.prepareStatement(
                    "SELECT category FROM categories");

            ResultSet resultSet = statement.executeQuery();

            // If content contains "category"
            boolean found = false;
            while (resultSet.next()) {
                if (task.getContent().contains(resultSet.getString(1))
                            || task.getContent().substring(0,1).contains("_")) {
                    found = true;
                }
            }

            if (!found) {
                statement = connection.prepareStatement(
                        "SELECT category FROM reference_list WHERE item = ?");

                statement.setString(1, task.getContent());

                resultSet = statement.executeQuery();

                //TODO improve?

                while (resultSet.next()) {
                    category = resultSet.getString(1);
                }

                if (category.equalsIgnoreCase("")) {
                    category = "_";
                    addUnknownTask(DbConnection.getConnection(), task.getContent());
                }

                task.setContent(category.concat(task.getContent()));

                UpdateList.addRenamedTask(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }
}