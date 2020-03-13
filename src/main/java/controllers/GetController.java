package controllers;

import enums.RequestMethod;
import enums.Target;
import models.Project;
import models.Task;
import models.TodoistCatalog;
import org.json.JSONArray;
import org.json.JSONObject;
import services.ConnectionService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GetController {
    public static void get() {
        getProjects();
        ConnectionController.disconnect();

        getTasks();
        ConnectionController.disconnect();
    }

    public static void getProjects() {
        ConnectionController.connect(Target.PROJECTS, RequestMethod.GET);

        JSONArray projects = getJSON();

        projects.forEach(project -> {
            JSONObject obj = (JSONObject) project;
            new Project(obj.getBigInteger("id"), obj.getString("name"));
        });
    }

    public static void getTasks() {
        ConnectionController.connect(Target.TASKS, RequestMethod.GET);

        JSONArray tasks = getJSON();
        if (tasks != null) {
            tasks.forEach(task -> {
                JSONObject obj = (JSONObject) task;
                if (TodoistCatalog.getProjects().size() != 0) {
                    new Task(obj.getBigInteger("id"), obj.getBigInteger("project_id"), obj.getString("content"));
                } else {
                    new Task(obj.getBigInteger("id"), obj.getString("content"));
                }
            });
        }
    }

    private static JSONArray getJSON() {
        try {
            BufferedReader in = new BufferedReader(
                    //FIXME - getConnection
                    new InputStreamReader(ConnectionService.getConnection().getInputStream()));

            StringBuffer content = new StringBuffer();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            JSONArray jsonArray = new JSONArray(content.toString());

            in.close();

            return jsonArray;

        } catch (IOException e) {
            System.out.println("Read failed");
            e.printStackTrace();
        }
        return null;
    }
}
