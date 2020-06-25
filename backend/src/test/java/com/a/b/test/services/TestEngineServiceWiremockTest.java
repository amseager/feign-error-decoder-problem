package com.a.b.test.services;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

class TestEngineServiceWiremockTest extends IntegrationTestBase {

    @Autowired
    private TestEngineService testEngineService;

    @Test
    void test() {
        WireMockServer testEngineServer = new WireMockServer(options().port(4400));
        testEngineServer.start();
        configureFor("localhost", 4400);
        stubFor(get("/test")
                        .willReturn(aResponse().withStatus(504)));

//        In this case where the rest client is built via the feign builder
//        this test will not test the right thing.
//        To test the ErrorDecoder start the app and run a GET request against http://localhost:4100/504

//        testEngineService.getReport("test", TEST_TEST_ENGINE);

        testEngineServer.stop();
    }
}
