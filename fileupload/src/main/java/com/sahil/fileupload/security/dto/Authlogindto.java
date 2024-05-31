package com.sahil.fileupload.security.dto;

import jakarta.validation.constraints.NotEmpty;

/** Authlogindto */
public record Authlogindto(
        @NotEmpty(message = "Username cannot be empty") String username,
        @NotEmpty(message = "Password must be entered") String password) {
}
