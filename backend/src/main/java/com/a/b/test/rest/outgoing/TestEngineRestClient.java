package com.a.b.test.rest.outgoing;

import com.a.b.test.configurations.TestEngineRestClientConfiguration;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "testEngineRestClient", url = "http://some-url-that-should-not-be.used", configuration = TestEngineRestClientConfiguration.class)
public interface TestEngineRestClient {

    @GetMapping(value = "/get-report/{uuid}")
    Response getReport(@PathVariable String uuid);
}
