package com.sahil.fileupload.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sahil.fileupload.security.dto.Authlogindto;
import com.sahil.fileupload.security.dto.Authsignupdto;

/**
 * AuthController
 */
@RestController
@RequestMapping(path = "/api/auth/")
public class AuthController {

    @PostMapping(path = "login")
    public String login(@RequestBody Authlogindto authlogindto) {
        return "login Success";
    }

    @PostMapping(path = "signup")
    public String signup(@RequestBody Authsignupdto authsignupdto){
        return "Created new user";
    }
}
