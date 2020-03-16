package controllers;

import models.ConnectionModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetControllerTest {
    ConnectionModel connectionModel;
    ConnectionController connectionController;
    GetController getter;


    @BeforeEach
    void setUp() {
        connectionModel = new ConnectionModel(
                "https://api.todoist.com/rest/v1/",
                "src/main/resources/token.txt");

        connectionController = new ConnectionController(connectionModel);
//        connectionController.connect();
//        getter = new GetController(connectionController);
    }

    @Test
    void T_getTasks() {
        GetController.getTasks(connectionController);
        assertEquals(200, connectionController.getResponseCode());
    }

    @Test
    void T_getProjects() {
        GetController.getProjects(connectionController);
        assertEquals(200, connectionController.getResponseCode());
    }

}