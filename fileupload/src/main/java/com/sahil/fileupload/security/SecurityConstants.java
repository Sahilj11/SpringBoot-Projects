package com.sahil.fileupload.security;

import java.security.Key;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

/** SecurityConstants */
public class SecurityConstants {

  public static final long JWT_EXPIRATION = 70000;
  public static final String JWT_SECRET = "secret";

  public static Key getKey(){
      byte[] keys = Decoders.BASE64.decode(JWT_SECRET);
      return Keys.hmacShaKeyFor(keys);
  }
}
