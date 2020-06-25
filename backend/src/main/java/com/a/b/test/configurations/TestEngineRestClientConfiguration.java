package com.a.b.test.configurations;

import feign.Contract;
import feign.Logger;
import feign.Request;
import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;

import java.util.Base64;

import static feign.Util.ISO_8859_1;

public class TestEngineRestClientConfiguration {

    private final TestEngineConfiguration testEngineConfig;

    public TestEngineRestClientConfiguration(TestEngineConfiguration testEngineConfig) {
        this.testEngineConfig = testEngineConfig;
    }

    @Bean
    public Contract feignContract() {
        return new SpringMvcContract();
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public Request.Options options() {
        return new Request.Options(1000, 1200);
    }

    @Bean
    public RequestInterceptor urlInterceptor() {
        return template -> {
            template.target(testEngineConfig.getAutoConfiguredUrl());
            String headerValue = "Basic " + encodeBase64(testEngineConfig);
            template.header("Authorization", headerValue);
        };
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }

    private static String encodeBase64(TestEngineConfiguration testEngineConfig) {
        return Base64.getEncoder().encodeToString((testEngineConfig.getAutoConfiguredUsername() + ":" + testEngineConfig.getAutoConfiguredPassword()).getBytes(ISO_8859_1));
    }

}
