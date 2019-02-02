package de.voder.resiliencedemo;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GreetingService {

    String url = "http://localhost:8081/hello";
    private RestTemplate restTemplate;

    public GreetingService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Greeting getGreeting() {
        ResponseEntity<String> hello = restTemplate.getForEntity(url, String.class);

        return new Greeting(hello.getBody());
    }
}
