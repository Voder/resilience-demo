package de.voder.resiliencedemo;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GreetingService {

    String url = "http://localhost:8081/hello";
    private RestTemplate restTemplate;
    private RetryTemplate retryTemplate;

    public GreetingService(RestTemplate restTemplate, RetryTemplate retryTemplate) {
        this.restTemplate = restTemplate;
        this.retryTemplate = retryTemplate;
    }

    @HystrixCommand(fallbackMethod = "greetingFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
    })
    public Greeting getGreeting() {

        ResponseEntity<String> hello = retryTemplate.execute(arg -> restTemplate.getForEntity(url, String.class));

        return new Greeting(hello.getBody());
    }

    public Greeting greetingFallback() {
        return new Greeting("Hello world!");
    }
}
