package de.voder.resiliencedemo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StartController {

    private GreetingService greetingService;

    public StartController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/start")
    public ResponseEntity<Greeting> start() {
        Greeting greeting = greetingService.getGreeting();
        return ResponseEntity.ok(greeting);
    }
}
