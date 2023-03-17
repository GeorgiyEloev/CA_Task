package com.example.serverForCA.security.jwt;

import com.example.serverForCA.modules.user.User;

public class JwtUserFactory {
  public JwtUserFactory() {
  }

  public static JwtUser create(User user) {
    return new JwtUser(
            user.getId(),
            user.getEmail(),
            user.getPassword()
    );
  }
}
