package com.example.serverForCA.exceptions;

public class ItemNotFoundException extends RuntimeException {
  public ItemNotFoundException(String msg, Throwable cause) {
    super(msg, cause);
  }

  public ItemNotFoundException(String msg) {
    super(msg);
  }
}
