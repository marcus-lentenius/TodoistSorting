package enums;

public enum Path {
    PROJECTS("projects"),
    TASKS("tasks");

    private String s;

    Path(String s) {
        this.s = s;
    }

    public String getPath() {
        return s;
    }
}
