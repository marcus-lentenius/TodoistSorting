package controllers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.ConnectionService;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GetControllerTest {

    @BeforeEach
    void setUp() {
        ConnectionController.initiate(
                "https://api.todoist.com/rest/v1/",
                "src/main/resources/token.txt");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getProjects() throws IOException {
        GetController.getProjects();
        assertTrue(ConnectionService.getConnection().getResponseCode() >= 200
        && ConnectionService.getConnection().getResponseCode() < 300);
    }

    @Test
    void getTasks() throws IOException {
        GetController.getTasks();
        assertTrue(ConnectionService.getConnection().getResponseCode() >= 200
                && ConnectionService.getConnection().getResponseCode() < 300);
    }
}