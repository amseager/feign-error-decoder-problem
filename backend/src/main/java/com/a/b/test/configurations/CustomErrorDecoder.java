package com.a.b.test.configurations;

import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

@Component
public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        Exception exception = defaultErrorDecoder.decode(methodKey, response);

        if (exception instanceof RetryableException) {
            return exception;
        }

        if (response.status() == 504) {
            return new RetryableException(response.status(), "some message", response.request().httpMethod(), null, response.request());
        }
        return exception;
    }
}
