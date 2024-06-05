package com.sahil.rest.controller;

import java.util.Arrays;
import java.util.List;

import org.hibernate.annotations.SourceType;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sahil.rest.dto.Person;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * HelloController
 */
@RestController
public class HelloController {

    @GetMapping(path = "/hello/{name}")
    public String hello(@PathVariable("name") String name) {
        return "Hello " + name;
    }

    @PostMapping(path = "/goodbye")
    public String goodBye(@RequestBody Person p) {
        return "GoodBye " + p.getName();
    }

    @GetMapping(path = "/get")
    public Person getPerson() {
        Person p = new Person();
        p.setName("Rahul");
        return p;
    }

    @GetMapping(path = "/getall")
    public List<Person> getAll(HttpServletRequest request) {
        Person p = new Person();
        p.setName("tristan");
        p.setAge(5);
        Person p2 = new Person();
        p2.setName("ram");
        p2.setAge(79);
        return Arrays.asList(p, p2);
    }

    @GetMapping(path = "/statustest")
    public void getStatus(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    @GetMapping(path = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] getPdf() {
        byte[] test = new byte[100];
        return test;
    }

}
