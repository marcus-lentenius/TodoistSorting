package controllers;

import enums.Target;
import models.Task;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.ConnectionService;

import java.io.IOException;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PostControllerTest {

    private Task task;

    @BeforeEach
    void setUp() {
        ConnectionController.initiate(
                "https://api.todoist.com/rest/v1/",
                "src/main/resources/token.txt");
        task = new Task(BigInteger.valueOf(1), "test");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void updateTasks() throws IOException {
        PostController.updateTasks(task, Target.TASKS);
        assertTrue(ConnectionService.getConnection().getResponseCode() >= 200
                && ConnectionService.getConnection().getResponseCode() < 300);
    }
}