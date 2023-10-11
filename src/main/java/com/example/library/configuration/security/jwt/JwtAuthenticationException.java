package com.example.library.configuration.security.jwt;

import javax.naming.AuthenticationException;

public class JwtAuthenticationException extends AuthenticationException {
  public JwtAuthenticationException(String msg) {
    super(msg);
  }
}
