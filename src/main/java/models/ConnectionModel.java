package models;


import controllers.TokenController;

public class ConnectionModel {
    private final String TOKEN;
    private final String API_URL;

    public ConnectionModel(String apiUrl, String tokenUrl) {
        this.TOKEN = TokenController.readInToken(tokenUrl);
        this.API_URL = apiUrl;
    }

    public String getTOKEN() {
        return TOKEN;
    }

    public String getAPI_URL() {
        return API_URL;
    }
}
