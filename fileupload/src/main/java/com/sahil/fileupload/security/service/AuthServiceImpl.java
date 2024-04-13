package com.sahil.fileupload.security.service;

import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.sahil.fileupload.entities.Roles;
import com.sahil.fileupload.entities.UserEntity;
import com.sahil.fileupload.repo.RolesRepo;
import com.sahil.fileupload.repo.UserRepo;
import com.sahil.fileupload.security.dto.Authsignupdto;

import lombok.RequiredArgsConstructor;

/**
 * AuthServiceImpl
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepo userRepo;
    private final PasswordEncoder encoder;
    private final UserEntity userEntity;
    private final RolesRepo roles;

    @Override
    public String login(String username, String password) {
    }

    @Override
    public UserEntity signup(Authsignupdto authsignupdto) {
        if (!authsignupdto.password().equals(authsignupdto.confirmPass())) {
           throw new RuntimeException("Password not matach"); 
        }
        userEntity.setUsername(authsignupdto.username());
        userEntity.setEmail(authsignupdto.email());
        userEntity.setPassword(encoder.encode(authsignupdto.password()));
        userEntity.setRoles(Set.of(roles.findByRole("FREE")));
        return userEntity;
    }

}
