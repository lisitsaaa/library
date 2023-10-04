package com.example.library.configuration.security.jwt;

import javax.naming.AuthenticationException;

public class JWTAuthenticationException extends AuthenticationException {
  public JWTAuthenticationException(String msg) {
    super(msg);
  }
}
