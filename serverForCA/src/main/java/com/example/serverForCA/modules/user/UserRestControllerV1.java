package com.example.serverForCA.modules.user;

import com.example.serverForCA.modules.user.dto.UserDto;
import com.example.serverForCA.modules.user.dto.UserUpdateDTO;
import com.example.serverForCA.modules.user.service.UserService;
import com.example.serverForCA.utils.annotations.EmailFromToken;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/users")
public class UserRestControllerV1 {

  private UserService userService;

  @Autowired
  public UserRestControllerV1(UserService userService) {
    this.userService = userService;
  }

  @Operation(summary = "Get info about user")
  @GetMapping("/me")
  ResponseEntity<UserDto> getMe(@EmailFromToken String email) {
    User user = userService.getUserByEmail(email);
    return new ResponseEntity<>(new UserDto(user), HttpStatus.OK);
  }

  @Operation(summary = "Update user")
  @PutMapping("/me")
  ResponseEntity<UserDto> updateMe(
          @EmailFromToken String email,
          @RequestBody UserUpdateDTO updateDto
  ) {
    User user = userService.updateUser(email, updateDto);
    return new ResponseEntity<>(new UserDto(user) , HttpStatus.OK);
  }
}
