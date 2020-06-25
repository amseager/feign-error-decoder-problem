package com.a.b.test.rest.outgoing;

import feign.Param;
import feign.RequestLine;
import feign.Response;

public interface TestEngineRestClient {

    @RequestLine(value = "GET /{uuid}")
    Response getReport(@Param("uuid") String uuid);
}
