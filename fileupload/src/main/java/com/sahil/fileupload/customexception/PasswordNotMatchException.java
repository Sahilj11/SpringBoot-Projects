package com.sahil.fileupload.customexception;

/** PasswordNotMatchException */
public class PasswordNotMatchException extends RuntimeException {
    String message;

    public PasswordNotMatchException(String message) {
        super(message);
    }
}
