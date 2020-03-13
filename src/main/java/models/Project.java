package models;

import java.math.BigInteger;
import java.util.ArrayList;

public class Project {
    private BigInteger id;
    private String name;
    private ArrayList<Task> tasks;

    public Project(BigInteger id, String name) {
        this.id = id;
        this.name = name;
        TodoistCatalog.addProject(this);
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public BigInteger getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}