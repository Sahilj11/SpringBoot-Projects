package com.sahil.fileupload.security.controller;

import com.sahil.fileupload.security.dto.Authlogindto;
import com.sahil.fileupload.security.dto.Authsignupdto;
import com.sahil.fileupload.security.service.AuthService;
import com.sahil.fileupload.storageconfig.StorageProperties;

import jakarta.validation.Valid;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** AuthController */
@RestController
@RequestMapping(path = "/api/auth/")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService aService;
    private final StorageProperties properties;

    @PostMapping(path = "login")
    public ResponseEntity<String> login(@Valid @RequestBody Authlogindto authlogindto) {
        ResponseEntity<String> login = aService.login(authlogindto.username(), authlogindto.password());
        if (!login.getStatusCode().equals(HttpStatus.OK)) {
            return ResponseEntity.status(login.getStatusCode()).build();
        }
        log.warn(SecurityContextHolder.getContext().getAuthentication().toString());
        return ResponseEntity.ok(login.getBody());
    }

    @PostMapping(path = "signup")
    public ResponseEntity<String> signup(@Valid @RequestBody Authsignupdto authsignupdto) {
        ResponseEntity<String> signup = aService.signup(authsignupdto);
        if (!signup.getStatusCode().equals(HttpStatus.OK)) {
            return ResponseEntity.status(signup.getStatusCode()).build();
        }
        try {
            Path p1 = Paths.get(properties.getLocation());
            Path p2 = Paths.get(authsignupdto.username());
            Files.createDirectory(p1.resolve(p2).toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(signup.getBody());
    }
}
