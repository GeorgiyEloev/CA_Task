package com.example.serverForCA.security.jwt;

import com.example.serverForCA.exceptions.UserNotFoundException;
import com.example.serverForCA.modules.user.User;
import com.example.serverForCA.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class JwtUserDetailsService implements UserDetailsService {

  private final UserService userService;

  @Autowired
  public JwtUserDetailsService(@Lazy UserService userService) {
    this.userService = userService;
  }
  @Override
  public UserDetails loadUserByUsername(String email) throws UserNotFoundException {
    boolean userExist = userService.existsByEmail(email);
    if (!userExist) {
      throw new UserNotFoundException("User with email: " + email + " not found");
    }

    User user = userService.getUserByEmail(email);
    JwtUser jwtUser = JwtUserFactory.create(user);
    return jwtUser;
  }
}
