package enums;

public enum Path {
    PROJECTS("projects"),
    TASKS("tasks"),
    SECTIONS("sections");

    private String s;

    Path(String s) {
        this.s = s;
    }

    public String getPath() {
        return s;
    }
}
