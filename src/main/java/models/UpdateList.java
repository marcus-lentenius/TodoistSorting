package models;

import java.util.ArrayList;

/**
 * Listing all tasks, the ones renamed and the ones not found in the reference list
 */

public class UpdateList {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static ArrayList<Task> renamedTasks = new ArrayList<>();
    private static ArrayList<Task> tasksToRename = new ArrayList<>();


    public static void addTasks(Task task) {
        tasks.add(task);
    }

    public static void addRenamedTask(Task task) {
        renamedTasks.add(task);
    }

    public static void addTasksToRename(Task task) {
        tasksToRename.add(task);
    }

    public static ArrayList<Task> getTasks() {
        return tasks;
    }

    public static ArrayList<Task> getRenamedTasks() {
        return renamedTasks;
    }

    public static ArrayList<Task> getTasksToRename() {
        return tasksToRename;
    }
}
