package models;

import java.util.ArrayList;

public class TodoistCatalog {
    private static TodoistCatalog instance = new TodoistCatalog();
    private static ArrayList<Project> projects = new ArrayList<>();
    private static ArrayList<Task> tasksWithoutProject = new ArrayList<>();

    private TodoistCatalog() {
    }

    public static TodoistCatalog getInstance() {
        return instance;
    }

    static void addProject(Project project) {
        projects.add(project);
    }

    public static ArrayList<Project> getProjects() {
        return projects;
    }

    static void addTask(Task task) {
        tasksWithoutProject.add(task);
    }

}
