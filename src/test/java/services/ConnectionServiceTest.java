package services;

import enums.RequestMethod;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.net.HttpURLConnection;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ConnectionServiceTest {

    private static ConnectionService connectionService;
    private static HttpURLConnection connection;

    static {
        connectionService = ConnectionService.getInstance();
        connectionService.setApiUrl("https://api.todoist.com/rest/v1/projects");
        connectionService.readToken("src/test/resources/token.txt");
        connectionService.setRequestMethod(RequestMethod.GET);
        connection = ConnectionService.getConnection();
        connectionService.connect();
    }

    @BeforeEach
    void beforeEach() {
    }

    @Order(1)
    @Test
    void connectTest() throws IOException {
        assertEquals(200, ConnectionService.getConnection().getResponseCode());
    }

    @Order(2)
    @Test
    void correctTokenTest() throws IOException {
        assertNotEquals(401, ConnectionService.getConnection().getResponseCode());
    }

}
