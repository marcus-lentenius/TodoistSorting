package enums;

public enum RequestMethod {
    GET("GET"),
    POST("POST"),
    DELETE("DELETE");

    private String request;

    RequestMethod(String request) {
        this.request = request;
    }

    public String getValue() {
        return request;
    }
}