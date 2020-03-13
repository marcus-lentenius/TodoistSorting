package models;

import java.math.BigInteger;

public class Task {
    private BigInteger id;
    private BigInteger project_id;
    private String content;

    public Task(BigInteger id, BigInteger project_id, String content) {
        this.id = id;
        this.project_id = project_id;
        this.content = content;
        addTaskToProject();
    }

    public Task(BigInteger id, String content) {
        this.id = id;
        this.content = content;
        TodoistCatalog.addTask(this);
    }

    private void addTaskToProject() {
        TodoistCatalog.getProjects().stream()
                .filter(project -> project.getId().equals(this.project_id))
                .findAny()
                .ifPresent(project -> project.getTasks().add(this));
    }

    public BigInteger getId() {
        return id;
    }

    public BigInteger getProject_id() {
        return project_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "{\n" +
                "    \"content\": \""+ content + "\",\n" +
                "    \"url\": \"https://todoist.com/showTask?id=" + id + "\"\n" + //FIXME var?
                "}";
    }
}