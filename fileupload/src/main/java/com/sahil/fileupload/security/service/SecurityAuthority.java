package com.sahil.fileupload.security.service;

import org.springframework.security.core.GrantedAuthority;

import com.sahil.fileupload.entities.Roles;
import lombok.AllArgsConstructor;

/**
 * SecurityAuthority
 */
@AllArgsConstructor
public class SecurityAuthority implements GrantedAuthority {

    private Roles roles;

    @Override
    public String getAuthority() {
        return roles.getRole();
    }

}
