package com.sahil.fileupload.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/** testingcont */
@RestController
public class testingcont {


    @GetMapping(path = "/testing")
    public ResponseEntity<String> test2() {
        System.out.println("This method is working");
        return ResponseEntity.ok("Hello");
    }
}
