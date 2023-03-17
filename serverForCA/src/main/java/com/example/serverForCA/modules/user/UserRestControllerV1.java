package com.example.serverForCA.modules.user;

import com.example.serverForCA.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/users")
public class UserRestControllerV1 {

  private UserService userService;

  @Autowired
  public UserRestControllerV1(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/me")
  ResponseEntity getMe() {
    return new ResponseEntity("me", HttpStatus.OK);
  }

  @PutMapping("/me")
  ResponseEntity updateMe() {
    return new ResponseEntity("me", HttpStatus.OK);
  }
}
