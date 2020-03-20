package controllers;

import models.DbConnection;
import models.Task;
import models.UpdateList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    //TODO refactor
    public static void renameTask(Connection connection, Task task) {
        String category = "";

        // See if task is categorized
        try {
            statement = connection.prepareStatement(
                    "SELECT category FROM categories");

            ResultSet resultSet = statement.executeQuery();

            // If content contains "category"
            boolean found = false;
            while (resultSet.next()) {
                if (task.getContent().contains(resultSet.getString(1))
                        || task.getContent().substring(0, 1).contains("_")) {
                    found = true;
                }
            }

            if (!found) {
                statement = connection.prepareStatement(
                        "SELECT c.category FROM reference_list as r " +
                                "JOIN categories as c " +
                                "ON r.category_id = c.id " +
                                "WHERE item = ?");

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

                task.setContent(category.concat(task.getContent()
                        .substring(0,1).toUpperCase()) +
                        task.getContent().substring(1));

                UpdateList.addRenamedTask(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}