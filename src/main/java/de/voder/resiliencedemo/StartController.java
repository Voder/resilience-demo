package de.voder.resiliencedemo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StartController {

    @GetMapping("/start")
    public ResponseEntity<Greeting> start() {
        return ResponseEntity.ok(new Greeting("Huhu"));
    }
}
