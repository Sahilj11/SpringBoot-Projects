package com.sahil.fileupload.security.service;

import com.sahil.fileupload.entities.UserEntity;
import com.sahil.fileupload.security.dto.Authsignupdto;

/**
 * AuthService
 */
public interface AuthService {

    String login(String username, String password);

    UserEntity signup(Authsignupdto authsignupdto);
}
