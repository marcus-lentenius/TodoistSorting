package models;

import java.util.ArrayList;

public class Catalog {
    private static Catalog instance = new Catalog();
    private static ArrayList<Project> projects = new ArrayList<>();
    private static ArrayList<Task> tasksWithoutProject = new ArrayList<>();
    private static ArrayList<Task> tasksToRename = new ArrayList<>();

    public static void addTasksToRename(Task task) {
        tasksToRename.add(task);
    }

    public static ArrayList<Task> getTasksToRename() {
        return tasksToRename;
    }

    private Catalog() {
    }

    public static Catalog getInstance() {
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
