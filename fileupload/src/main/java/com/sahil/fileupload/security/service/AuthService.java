package com.sahil.fileupload.security.service;

import com.sahil.fileupload.entities.UserEntity;
import com.sahil.fileupload.security.dto.Authsignupdto;
import com.sahil.fileupload.security.jwt.AuthenticationResponse;

/**
 * AuthService
 */
public interface AuthService {

    void login(String username, String password);

    String signup(Authsignupdto authsignupdto);
}
