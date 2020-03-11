package services;

import enums.RequestMethod;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class ConnectionService {
    public static ConnectionService instance = new ConnectionService();
    private static HttpURLConnection connection;

    private StringBuffer token = new StringBuffer();
    private String apiUrl;

    private RequestMethod requestMethod;

    private ConnectionService() {
    }

    public void connect() {
        try {
            URL url = new URL(apiUrl);

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Authorization", "Bearer " + token);
            connection.setRequestMethod(String.valueOf(requestMethod));

            connection.connect();
        } catch (ProtocolException e) {
            //TODO rerun
            System.out.println("Request failed");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Open URL connection failed");
            e.printStackTrace();
        }
    }

    public static ConnectionService getInstance() {
        return instance;
    }

    public static HttpURLConnection getConnection() {
        return connection;
    }

    public void readToken(String url) {
        try {
            FileInputStream inputStream = new FileInputStream(url);

            int c;
            while ((c = inputStream.read()) != -1) {
                token.append((char) c);
            }

            inputStream.close();

        } catch (FileNotFoundException e) {
            // TODO Rerun
            System.out.println("File not found");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Stream failed");
            e.printStackTrace();
        }
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public void setRequestMethod(RequestMethod requestMethod) {
        this.requestMethod = requestMethod;
    }
}
