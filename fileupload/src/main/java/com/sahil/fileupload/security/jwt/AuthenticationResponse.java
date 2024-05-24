package com.sahil.fileupload.security.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * AuthenticationResponse
 */
@RequiredArgsConstructor
@Getter
public class AuthenticationResponse {

    private final String token;
    
}
