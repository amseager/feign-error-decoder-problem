package com.a.b.test.rest.incoming;

import com.a.b.test.configurations.TestEngines;
import com.a.b.test.services.TestEngineService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImportTestsController {
    private final TestEngineService testEngineService;

    public ImportTestsController(TestEngineService testEngineService) {this.testEngineService = testEngineService;}

    @GetMapping(value = "/{any}")
    public void test(@PathVariable("any") String any) {
        testEngineService.getReport(any, TestEngines.TEST2_TEST_ENGINE);
    }
}
