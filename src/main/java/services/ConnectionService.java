//package services;
//
//import enums.RequestMethod;
//import models.TodoistConnection;
//
//import java.io.IOException;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//public class ConnectionService {
//    private static TodoistConnection todoistConnection = TodoistConnection.getInstance();
//    private static HttpURLConnection connection;
//    private static String token = todoistConnection.getToken();
//    private static String apiUrl = todoistConnection.getApiUrl();
//
//    public static void connect(RequestMethod requestMethod) {
//        try {
//            URL url = new URL(apiUrl.concat(
//                    String.valueOf(todoistConnection.getTarget()))
//                    .toLowerCase());
//            connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod(String.valueOf(requestMethod));
//
//            connection.setRequestProperty("Authorization", "Bearer " + token);
//
//            if (requestMethod == RequestMethod.GET) {
//                connection.connect();
//            } else if (requestMethod == RequestMethod.POST) {
//                connection.setRequestProperty("Content-Type", "application/json");
//                connection.setRequestProperty("X-Request", "$(uuidgen)");
//                connection.setDoOutput(true);
//            }
//        } catch (IOException e) {
//            //FIXME
//            e.printStackTrace();
//        }
//    }
//
//    public static HttpURLConnection getConnection() {
//        return connection;
//    }
//}
