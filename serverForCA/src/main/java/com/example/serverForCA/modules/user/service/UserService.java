package com.example.serverForCA.modules.user.service;

import com.example.serverForCA.modules.user.User;
import com.example.serverForCA.modules.user.dto.UserUpdateDTO;

public interface UserService {

  User getUser(Long id);

  User getUserByEmail(String email);

  User updateUser(String email, UserUpdateDTO update);

  User createNewUser(User user);

  boolean existsByEmail(String email);
}
