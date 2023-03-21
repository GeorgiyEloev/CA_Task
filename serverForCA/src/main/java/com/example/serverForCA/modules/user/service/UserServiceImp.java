package com.example.serverForCA.modules.user.service;

import com.example.serverForCA.exceptions.UserNotFoundException;
import com.example.serverForCA.modules.user.User;
import com.example.serverForCA.modules.user.UserRepository;
import com.example.serverForCA.modules.user.dto.UserUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserServiceImp(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User getUser(Long id) {
    return userRepository
            .findById(id)
            .orElseThrow(() -> new UserNotFoundException("User not found!"));
  }

  @Override
  public User getUserByEmail(String email) {
    return userRepository
            .findByEmail(email)
            .orElseThrow(() -> new UserNotFoundException("User not found!"));
  }


  @Override
  public User updateUser(String email, UserUpdateDTO updateDto) {
    User oldUser = getUserByEmail(email);

    oldUser.setAge(updateDto.getAge() == null? oldUser.getAge(): updateDto.getAge());
    oldUser.setName(updateDto.getName() == null ? oldUser.getName(): updateDto.getName());

    return userRepository.save(oldUser);
  }

  @Override
  public User createNewUser(User user) {
    return userRepository.save(user);
  }

  @Override
  public boolean existsByEmail(String email) {
    return userRepository.existsByEmail(email);
  }
}
