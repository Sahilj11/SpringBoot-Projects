package com.sahil.fileupload.security.service;

import com.sahil.fileupload.customexception.PasswordNotMatchException;
import com.sahil.fileupload.entities.UserEntity;
import com.sahil.fileupload.repo.RolesRepo;
import com.sahil.fileupload.repo.UserRepo;
import com.sahil.fileupload.security.dto.Authsignupdto;
import com.sahil.fileupload.security.jwt.JwtService;
import com.sahil.fileupload.security.secconfig.CustomUserDetailsService;
import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/** AuthServiceImpl */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepo userRepo;
    private final PasswordEncoder encoder;
    private final CustomUserDetailsService userDetailsService;
    private final RolesRepo roles;
    private final JwtService jService;

    @Override
    public ResponseEntity<String> login(String username, String password) {
        try {
            UserDetails userByUsername = userDetailsService.loadUserByUsername(username);
            if (encoder.matches(password, userByUsername.getPassword())) {
                Optional<UserEntity> byUsername = userRepo.findByUsername(userByUsername.getUsername());
                String jwtGenerate = jService.jwtGenerate(byUsername.orElseThrow());
                return ResponseEntity.ok(jwtGenerate);
            } else {
                return new ResponseEntity<>("Incorrect password", HttpStatus.BAD_REQUEST);
            }
        } catch (UsernameNotFoundException e) {
            return new ResponseEntity<>("Username not found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<String> signup(Authsignupdto authsignupdto) {
        if (!authsignupdto.password().equals(authsignupdto.confirmPass())) {
            throw new PasswordNotMatchException("Password not match");
        }
        try {
            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(authsignupdto.username());
            userEntity.setEmail(authsignupdto.email());
            userEntity.setPassword(encoder.encode(authsignupdto.password()));
            userEntity.setRoles(Set.of(roles.findByRole("FREE")));
            userEntity = userRepo.save(userEntity);
            String token = jService.jwtGenerate(userEntity);
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return new ResponseEntity<String>("Could not create a user", HttpStatus.NOT_MODIFIED);
        }
    }
}
