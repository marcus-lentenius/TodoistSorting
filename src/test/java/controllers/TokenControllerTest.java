package controllers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TokenControllerTest {

    @Test
    void readInToken() {

        assertEquals("test", TokenController.readInToken("src/test/resources/token.txt"));
    }
}