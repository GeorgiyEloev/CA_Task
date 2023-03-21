package com.example.serverForCA.modules.auth.service;

import com.example.serverForCA.exceptions.EmailNotFoundException;
import com.example.serverForCA.exceptions.UserAlreadyExistException;
import com.example.serverForCA.modules.auth.dto.RegistrationDto;
import com.example.serverForCA.modules.user.User;
import com.example.serverForCA.modules.user.service.UserService;
import com.example.serverForCA.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImp implements AuthService {

  private final AuthenticationManager authenticationManager;

  private final JwtTokenProvider jwtTokenProvider;
  private final UserService userService;

  @Autowired
  public AuthServiceImp(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
    this.authenticationManager = authenticationManager;
    this.jwtTokenProvider = jwtTokenProvider;
    this.userService = userService;
  }

  @Override
  public String authorization(String email, String password) {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));

    if (!userService.existsByEmail(email)) {
      throw new EmailNotFoundException("User with email: " + email + " not found");
    }

    return jwtTokenProvider.createToken(email);
  }

  @Override
  public void registration(RegistrationDto regDto) {
    if (userService.existsByEmail(regDto.getEmail())) {
      throw  new UserAlreadyExistException("User already exist!");
    }

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    String encodePassword = bCryptPasswordEncoder.encode(regDto.getPassword());

    User user = new User(regDto.getName(), regDto.getAge(), regDto.getEmail(), encodePassword);

    userService.createNewUser(user);
  }
}
