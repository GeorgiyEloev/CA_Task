package com.example.serverForCA.modules.auth;

import com.example.serverForCA.utils.constans.Response;
import com.example.serverForCA.utils.constans.Token;
import com.example.serverForCA.modules.auth.dto.LoginDto;
import com.example.serverForCA.modules.auth.dto.RegistrationDto;
import com.example.serverForCA.modules.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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


  @Operation(summary = "Login user")
  @PostMapping("/login")
  public ResponseEntity<Token> login(@RequestBody @Valid LoginDto loginDto) {
    String token = authService.authorization(loginDto.getEmail(), loginDto.getPassword());
    return new ResponseEntity<>(new Token(token), HttpStatus.OK);
  }

  @Operation(summary = "Registration user")
  @PostMapping("/register")
  public ResponseEntity<Response> register(@RequestBody @Valid RegistrationDto regDto) {
      authService.registration(regDto);
      return new ResponseEntity<>(new Response("Registration is successful"), HttpStatus.OK);
  }
}
