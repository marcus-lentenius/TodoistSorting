package models;

public class Task {
    private Long id;
    private Long project_id;
    private String content;
    private Long section_id;

    public Task(Long id, Long project_id, String content) {
        this.id = id;
        this.project_id = project_id;
        this.content = content;
        addTaskToProject();
    }

    public Task(Long id, String content) {
        this.id = id;
        this.content = content;
        Catalog.addTask(this);
    }

    private void addTaskToProject() {
        Catalog.getProjects().stream()
                .filter(project -> project.getId().equals(this.project_id))
                .findAny()
                .ifPresent(project -> project.addTask(this));
    }

    public Long getId() {
        return id;
    }

    public Long getProject_id() {
        return project_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getSection_id() {
        return section_id;
    }

    public void setSection_id(Long section_id) {
        this.section_id = section_id;
    }

    @Override
    public String toString() {
        return "{\n" +
                "    \"content\": \""+ content + "\",\n" +
                "    \"section_id\": \""+ section_id + "\",\n" +
                "    \"url\": \"https://todoist.com/showTask?id=" + id + "\"\n" + //FIXME var?
                "}";
    }
}