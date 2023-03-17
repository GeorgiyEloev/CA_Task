package com.example.serverForCA.modules.auth.service;

import com.example.serverForCA.exceptions.UserAlreadyExistException;
import com.example.serverForCA.modules.user.User;
import com.example.serverForCA.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImp implements AuthService {

  private final UserService userService;

  @Autowired
  public AuthServiceImp(UserService userService) {
    this.userService = userService;
  }

  @Override
  public String authorization(String username, String password) {
    return null;
  }

  @Override
  public void registration(User user) {

    if (userService.existsByEmail(user.getEmail())) {
      throw  new UserAlreadyExistException("User already exist!");
    }

    userService.createNewUser(user);
  }
}
