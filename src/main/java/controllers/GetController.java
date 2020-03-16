package controllers;

import enums.Path;
import models.Catalog;
import models.Project;
import models.Task;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GetController {
    public static void getTasks(ConnectionController connectionController) {
        connectionController.get(Path.TASKS.getPath());

        JSONArray tasks = getJSON(connectionController);

        if (tasks != null) {
            tasks.forEach(task -> {
                JSONObject obj = (JSONObject) task;
                if (Catalog.getProjects().size() != 0) {
                    new Task(obj.getLong("id"), obj.getLong("project_id"), obj.getString("content"));
                } else {
                    new Task(obj.getLong("id"), obj.getString("content"));
                }
            });
        }
    }

    public static void getProjects(ConnectionController connectionController) {
        connectionController.get(Path.PROJECTS.getPath());

        JSONArray projects = getJSON(connectionController);

        projects.forEach(project -> {
            JSONObject obj = (JSONObject) project;
            new Project(obj.getLong("id"), obj.getString("name"));
        });
    }

    private static JSONArray getJSON(ConnectionController connectionController) {
        try {
            BufferedReader in = new BufferedReader(
                    //FIXME - getConnection
                    new InputStreamReader(connectionController.getInputStream()));

            StringBuilder content = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            JSONArray jsonArray = new JSONArray(content.toString());

            in.close();

            return jsonArray;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}