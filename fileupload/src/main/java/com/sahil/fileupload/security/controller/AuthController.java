package com.sahil.fileupload.security.controller;

import com.sahil.fileupload.security.dto.Authlogindto;
import com.sahil.fileupload.security.dto.Authsignupdto;
import com.sahil.fileupload.security.service.AuthService;
import com.sahil.fileupload.storageconfig.StorageProperties;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** AuthController */
@RestController
@RequestMapping(path = "/api/auth/")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService aService;
    private final StorageProperties properties;

    @PostMapping(path = "login")
    public String login(@RequestBody Authlogindto authlogindto) {
        aService.login(authlogindto.username(), authlogindto.password());
        return "login";
    }

    @PostMapping(path = "signup")
    public String signup(@RequestBody Authsignupdto authsignupdto) {
        aService.signup(authsignupdto);
        try {
            Files.createDirectories(Paths.get(properties.getLocation()+"/"+authsignupdto.username()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "authsuccess";
    }
}
