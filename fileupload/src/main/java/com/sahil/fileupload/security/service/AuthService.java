package com.sahil.fileupload.security.service;

import com.sahil.fileupload.security.dto.Authsignupdto;

/**
 * AuthService
 */
public interface AuthService {

    String login(String username, String password);

    String signup(Authsignupdto authsignupdto);
}
