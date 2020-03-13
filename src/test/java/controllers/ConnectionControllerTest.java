package controllers;

import enums.RequestMethod;
import enums.Target;
import org.junit.jupiter.api.*;
import services.ConnectionService;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ConnectionControllerTest {

    @BeforeEach
    void setUp() {
        ConnectionController.initiate(
                "https://api.todoist.com/rest/v1/",
                "src/main/resources/token.txt");
    }


    @AfterEach
    void tearDown() {
    }

    @Order(1)
    @Test
    void readInToken() {
        assertEquals("test", ConnectionController.readInToken("src/test/resources/token.txt"));
    }

    @Order(2)
    @Test
    void connectWithProjectsTest() throws IOException {
        ConnectionController.connect(Target.PROJECTS, RequestMethod.GET);
        //FIXME getConnection
        assertEquals(200, ConnectionService.getConnection().getResponseCode());
    }

    @Order(3)
    @Test
    void connectWithTasksTest() throws IOException {
        ConnectionController.disconnect();
        ConnectionController.connect(Target.TASKS, RequestMethod.GET);
        //FIXME getConnection
        assertEquals(200, ConnectionService.getConnection().getResponseCode());
    }

    @Order(4)
    @Test
    void disconnectTest() throws IOException {
        //FIXME getConnection
        assertEquals(200, ConnectionService.getConnection().getResponseCode());
    }

}