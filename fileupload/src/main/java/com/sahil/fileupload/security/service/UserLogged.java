package com.sahil.fileupload.security.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * UserLogged
 */
public class UserLogged {

   public String getLogged(){
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       return authentication.getName();
   } 
}
