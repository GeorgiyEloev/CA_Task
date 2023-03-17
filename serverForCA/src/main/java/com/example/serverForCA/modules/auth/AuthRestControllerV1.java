package com.example.serverForCA.modules.auth;

import com.example.serverForCA.modules.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthRestControllerV1 {

  private final AuthService authService;

  @Autowired
  public AuthRestControllerV1(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/login")
  public ResponseEntity login() {
    return new ResponseEntity("token", HttpStatus.OK);
  }

  @PostMapping("/register")
  public ResponseEntity signup() {
    return new ResponseEntity("OK", HttpStatus.OK);
  }
}
