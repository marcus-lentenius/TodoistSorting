package controllers;

import enums.Path;
import models.ConnectionModel;
import models.Task;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ConnectionControllerTest {

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


    @AfterEach
    void tearDown() {
    }

    @Test
    void T_get() {
        connectionController.get(Path.TASKS.getPath());
        assertEquals(200, connectionController.getResponseCode());
    }
}