package com.example.serverForCA.modules.user.service;

import com.example.serverForCA.modules.user.User;

public interface UserService {

  User getUser(Long id);

  User getUserByEmail(String email);

  User updateUser(User user);

  User createNewUser(User user);

  boolean existsByEmail(String email);
}
