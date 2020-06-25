# feign-error-decoder-problem

I tried three ways to get the ErrorDecoder to work but none were successful. Look at the commit history to see those three attempts.

## Desired outcome

When you send a GET request against `http://localhost:4100/504` the `TestEngineRestClient` sends a GET request against `http://httpstat.us/504`.
That second request will get a 504 response. Now the decode() method of the ErrorDecoder should step in and throw a RetryableException 
which in turn would trigger retrying to call that `http://httpstat.us/504` again.
