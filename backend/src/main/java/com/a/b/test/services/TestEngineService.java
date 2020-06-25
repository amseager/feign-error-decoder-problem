package com.a.b.test.services;

import java.util.HashMap;

import com.a.b.test.configurations.TestEngineConfiguration;
import com.a.b.test.configurations.TestEngines;
import com.a.b.test.configurations.TestEnginesConfigItems;
import com.a.b.test.rest.outgoing.TestEngineRestClient;
import feign.Response;
import org.springframework.stereotype.Service;

@Service
public class TestEngineService {
    private final TestEngineRestClient testEngineRestClient;
    private final TestEngineConfiguration testEngineConfiguration;

    public TestEngineService(TestEngineRestClient testEngineRestClient, TestEngineConfiguration testEngineConfiguration) {
        this.testEngineRestClient = testEngineRestClient;
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
