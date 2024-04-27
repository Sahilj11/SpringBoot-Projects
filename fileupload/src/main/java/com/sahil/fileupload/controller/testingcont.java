package com.sahil.fileupload.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** testingcont */
@RestController
@RequestMapping(path = "/test")
public class testingcont {

    @GetMapping(path = "/")
    public String test() {
        return "test";
    }
}
