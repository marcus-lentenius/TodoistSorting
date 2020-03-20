package controllers;

import enums.RequestMethod;
import models.ConnectionModel;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionController {

    private HttpURLConnection connection;

    private final String TOKEN;
    private final String API_URL;

    public ConnectionController(ConnectionModel connectionModel) {
        this.TOKEN = connectionModel.getTOKEN();
        this.API_URL = connectionModel.getAPI_URL();
    }


    private void connect(String pathExtension, RequestMethod requestMethod) {
        try {
            URL url = new URL(API_URL.concat(pathExtension));

            this.connection = (HttpURLConnection) url.openConnection();
            this.connection.setRequestMethod(String.valueOf(requestMethod));
            this.connection.setRequestProperty("Authorization", "Bearer " + TOKEN);
//
            if (requestMethod == RequestMethod.GET) {
                this.connection.connect();
            } else if (requestMethod == RequestMethod.DELETE) {
                this.connection.connect();
                System.out.println(connection.getResponseCode());
            } else if (requestMethod == RequestMethod.POST) {
                this.connection.setRequestProperty("Content-Type", "application/json");
                this.connection.setRequestProperty("X-Request", "$(uuidgen)");
                this.connection.setDoOutput(true);
            }

        } catch (IOException e) {
            //FIXME
            e.printStackTrace();
        }
    }

    public void delete(String path) {
        connect(path, RequestMethod.DELETE);
    }

    public HttpURLConnection getConnection() {
        return this.connection;
    }

    public int getResponseCode() {
        try {
            return this.connection.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public InputStream getInputStream() {
        try {
            return connection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void get(String path) {
        connect(path, RequestMethod.GET);
    }
//TODO path? two methods
    public void post(String path) {
        connect(path,RequestMethod.POST);
    }
}
