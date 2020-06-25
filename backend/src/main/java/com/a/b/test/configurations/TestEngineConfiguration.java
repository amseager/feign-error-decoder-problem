package com.a.b.test.configurations;

import java.net.URI;
import java.util.HashMap;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Data
@Component
@RefreshScope
@ConfigurationProperties(TestEngineConfiguration.PREFIX)
public class TestEngineConfiguration {
    public static final String PREFIX = "testengine";
    private String url;
    private String autoConfiguredUrl;
    private String autoConfiguredUsername;
    private String autoConfiguredPassword;
    private HashMap<TestEngines, HashMap<TestEnginesConfigItems, String>> testEngines;

    public URI getUri() {
        return URI.create(this.url);
    }
}
