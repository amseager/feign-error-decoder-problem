package com.a.b.test.services;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.a.b.test.configurations.TestEngines.TEST_TEST_ENGINE;
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

        testEngineService.getReport("test", TEST_TEST_ENGINE);

        testEngineServer.stop();
    }
}
