package com.example.serverForCA.exceptions;

import org.springframework.security.core.AuthenticationException;

public class UserAlreadyExistException extends AuthenticationException {
  public UserAlreadyExistException(String msg, Throwable cause) {
    super(msg, cause);
  }

  public UserAlreadyExistException(String msg) {
    super(msg);
  }
}
