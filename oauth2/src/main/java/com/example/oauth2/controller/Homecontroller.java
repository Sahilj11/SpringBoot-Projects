package com.example.oauth2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/** Homecontroller */
@RestController
public class Homecontroller {

    @GetMapping(path = "/")
    public String home() {
        return "Home";
    }

    @GetMapping(path = "/test")
    public String test() {
        return "test";
    }
}
