package controllers;

import enums.RequestMethod;
import models.ConnectionModel;
import models.Task;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionController {

    private HttpURLConnection connection;

    private Task taskToPost;

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
            } else if (requestMethod == RequestMethod.POST) {
                this.connection.setRequestProperty("Content-Type", "application/json");
                this.connection.setRequestProperty("X-Request", "$(uuidgen)");
                this.connection.setDoOutput(true);

//                postData();
            }

        } catch (IOException e) {
            //FIXME
            e.printStackTrace();
        }
    }

    private void postData() {
        try {
        OutputStream os = this.connection.getOutputStream();
            os.write(taskToPost.toString().getBytes());
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public void post(String path) {
        connect(path,RequestMethod.POST);
    }
}
