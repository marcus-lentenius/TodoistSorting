package models;


import enums.Target;

public class TodoistConnection {
    private static TodoistConnection instance;

    private String token;
    private String apiUrl;
    private Target target;

    private TodoistConnection() {
    }

    public static TodoistConnection getInstance() {
        if (instance == null) {
            instance = new TodoistConnection();
        }
        return instance;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public String getToken() {
        return String.valueOf(token);
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public Target getTarget() {
        return target;
    }
}
