package com.a.b.test.services;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {"logging.level.com.a.b.test=DEBUG"})
@ActiveProfiles("test")
public class IntegrationTestBase {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.port = this.port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

}
