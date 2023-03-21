package com.example.serverForCA.modules.auth.service;

import com.example.serverForCA.modules.auth.dto.RegistrationDto;
import com.example.serverForCA.modules.user.User;

public interface AuthService {
  String authorization(String email, String password);

  void registration(RegistrationDto user);
}
