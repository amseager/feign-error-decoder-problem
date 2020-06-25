package com.a.b.test.services;

import java.util.HashMap;

import com.a.b.test.configurations.CustomErrorDecoder;
import com.a.b.test.configurations.CustomRetryer;
import com.a.b.test.configurations.TestEngineConfiguration;
import com.a.b.test.configurations.TestEngines;
import com.a.b.test.configurations.TestEnginesConfigItems;
import com.a.b.test.rest.outgoing.TestEngineRestClient;
import feign.Feign;
import feign.Logger;
import feign.Response;
import feign.slf4j.Slf4jLogger;
import org.springframework.stereotype.Service;

@Service
public class TestEngineService {
    private final TestEngineRestClient testEngineRestClient;
    private final TestEngineConfiguration testEngineConfiguration;

    public TestEngineService(TestEngineConfiguration testEngineConfiguration) {
        this.testEngineRestClient = Feign.builder()
                                         .logger(new Slf4jLogger(TestEngineRestClient.class))
                                         .logLevel(Logger.Level.FULL)
                                         .errorDecoder(new CustomErrorDecoder())
                                         .retryer(new CustomRetryer())
                                         .target(TestEngineRestClient.class, "http://httpstat.us");
        this.testEngineConfiguration = testEngineConfiguration;
    }

    private void setConfigurablePropertiesToSpecificTestEngineProperties(TestEngines testEngine) {
        HashMap<TestEnginesConfigItems, String> testEngineConfig = testEngineConfiguration.getTestEngines().get(testEngine);

        testEngineConfiguration.setAutoConfiguredUrl(testEngineConfig.get(TestEnginesConfigItems.URL));
        testEngineConfiguration.setAutoConfiguredUsername(testEngineConfig.get(TestEnginesConfigItems.USERNAME));
        testEngineConfiguration.setAutoConfiguredPassword(testEngineConfig.get(TestEnginesConfigItems.PASSWORD));
    }

    public Response getReport(String uuid, TestEngines testEngine) {
        setConfigurablePropertiesToSpecificTestEngineProperties(testEngine);
        return testEngineRestClient.getReport(uuid);
    }
}
