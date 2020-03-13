package controllers;

import enums.RequestMethod;
import enums.Target;
import models.TodoistConnection;
import services.ConnectionService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ConnectionController {

    private static TodoistConnection todoistConnection = TodoistConnection.getInstance();

    public static void initiate(String apiUrl, String tokenUrl) {
        todoistConnection.setApiUrl(apiUrl);
        todoistConnection.setToken(readInToken(tokenUrl));
    }

    public static void connect(Target target, RequestMethod requestMethod){
        todoistConnection.setTarget(target);
        ConnectionService.connect(requestMethod);
    }

    public static String readInToken(String tokenUrl) {
        StringBuffer token = new StringBuffer();

        try {
            FileInputStream inputStream = new FileInputStream(tokenUrl);

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

        return String.valueOf(token);
    }

    public static void disconnect() {
        ConnectionService.getConnection().disconnect();
    }
}
