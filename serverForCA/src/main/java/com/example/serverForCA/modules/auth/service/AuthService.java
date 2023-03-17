package com.example.serverForCA.modules.auth.service;

import com.example.serverForCA.modules.user.User;

public interface AuthService {
  String authorization(String username, String password);

  void registration(User user);
}
