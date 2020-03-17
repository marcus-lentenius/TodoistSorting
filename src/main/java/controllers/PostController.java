package controllers;

import enums.Path;
import models.Task;

import java.io.IOException;
import java.io.OutputStream;

public class PostController {


    public static void post(Path path, Task task, ConnectionController connectionController) {
        connectionController.post(path.getPath());
        output(task, connectionController);
    }

    public static void update(Path path, Task task, ConnectionController connectionController) {
        connectionController.post(path.getPath()
                .concat("/")
                .concat(String.valueOf(task.getId())));

        output(task, connectionController);
    }

    private static void output(Task task, ConnectionController connectionController) {
        try {
            OutputStream os = connectionController.getConnection().getOutputStream();
            os.write(task.toString().getBytes());
            os.flush();
            os.close();

            System.out.println(connectionController.getResponseCode());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//        ConnectionController.connect(target, RequestMethod.POST);
//
//        try {
//            OutputStream outputStream = ConnectionService.getConnection().getOutputStream();
//            outputStream.write(task.toString().getBytes());
//            outputStream.flush();
//            outputStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
