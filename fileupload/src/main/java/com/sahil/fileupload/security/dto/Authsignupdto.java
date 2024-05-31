package com.sahil.fileupload.security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

/** Authsignupdto */
public record Authsignupdto(
        @NotEmpty(message = "username cannot be empty") String username,
        @Size(min = 8, max = 20) @NotEmpty() String password,
        @NotEmpty() String confirmPass,
        @Email(message = "Enter valid email address") String email) {
}
