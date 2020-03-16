package models;

import java.util.ArrayList;

public class Project {
    private Long id;
    private String name;
    private ArrayList<Task> tasks;

    public Project(Long id, String name) {
        this.id = id;
        this.name = name;
        Catalog.addProject(this);
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}