package com.sahil.fileupload.security.service;

import com.sahil.fileupload.entities.UserEntity;
import com.sahil.fileupload.repo.RolesRepo;
import com.sahil.fileupload.repo.UserRepo;
import com.sahil.fileupload.security.dto.Authsignupdto;

import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/** AuthServiceImpl */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepo userRepo;
    private final PasswordEncoder encoder;
    private final UserDetailsService userDetailsService;
    private final RolesRepo roles;

    @Override
    public void login(String username, String password) {
            userDetailsService.loadUserByUsername(username);
    }

    @Override
    public void signup(Authsignupdto authsignupdto) {
        if (!authsignupdto.password().equals(authsignupdto.confirmPass())) {
            throw new RuntimeException("Password not match");
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(authsignupdto.username());
        userEntity.setEmail(authsignupdto.email());
        userEntity.setPassword(encoder.encode(authsignupdto.password()));
        userEntity.setRoles(Set.of(roles.findByRole("FREE")));
        userRepo.save(userEntity);
    }
}
