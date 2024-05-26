package com.sahil.fileupload.security.service;

import org.springframework.http.ResponseEntity;

import com.sahil.fileupload.security.dto.Authsignupdto;

/**
 * AuthService
 */
public interface AuthService {

    ResponseEntity<String> login(String username, String password);

    ResponseEntity<String> signup(Authsignupdto authsignupdto);
}
