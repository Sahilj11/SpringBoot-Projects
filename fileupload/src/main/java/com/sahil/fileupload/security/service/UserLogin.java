package com.sahil.fileupload.security.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import jakarta.security.auth.message.AuthException;

/**
 * UserLogin
 */
@Component
public class UserLogin {

   public String getUserName() throws AuthException{
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       if (authentication != null && authentication.isAuthenticated()) {
           return authentication.getName();
       }else{
           throw new AuthException();
       }
   }
}
