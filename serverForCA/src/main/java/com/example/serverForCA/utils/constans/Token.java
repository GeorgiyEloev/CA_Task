package com.example.serverForCA.utils.constans;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Token {

  private String token;

  public Token() {
  }

  public Token(String token) {
    this.token = token;
  }
}
