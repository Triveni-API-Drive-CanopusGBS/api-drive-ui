package com.ttl.ITOapidrive.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, World!";
    }

    @GetMapping("/greet")
    public String greetUser(@RequestParam(name = "name", defaultValue = "User") String name) {
        return "Hello, " + name + "!";
    }
}

