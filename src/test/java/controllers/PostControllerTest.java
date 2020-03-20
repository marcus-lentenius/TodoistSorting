package controllers;

import models.ConnectionModel;
import models.Task;
import org.junit.jupiter.api.BeforeEach;

class PostControllerTest {

    private ConnectionModel connectionModel;
    private ConnectionController connectionController;
    private Task task;


    @BeforeEach
    void setUp() {
        connectionModel = new ConnectionModel(
                "https://api.todoist.com/rest/v1/",
                "src/main/resources/token.txt");

        connectionController = new ConnectionController(connectionModel);
        task = new Task(3751778066L, "testnew");
    }

//    @Test
//    void T_post() {
//        PostController.post(Path.TASKS,task,connectionController);
//        assertEquals(200, connectionController.getResponseCode());
//    }

//    @Test
//    void T_update() {
//        PostController.update(Path.TASKS,task,connectionController);
//        assertEquals(204, connectionController.getResponseCode());
//    }

}