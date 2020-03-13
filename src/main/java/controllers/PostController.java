package controllers;

import enums.RequestMethod;
import enums.Target;
import models.Task;
import services.ConnectionService;

import java.io.IOException;
import java.io.OutputStream;

public class PostController {

    public static void updateTasks(Task task, Target target) {
        ConnectionController.connect(target, RequestMethod.POST);

        try {
            OutputStream outputStream = ConnectionService.getConnection().getOutputStream();
            outputStream.write(task.toString().getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
