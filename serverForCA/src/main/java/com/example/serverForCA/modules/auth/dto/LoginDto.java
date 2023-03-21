package com.example.serverForCA.modules.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {

  @NotNull(message = "email must not be empty")
  @Email(message = "incorrect email")
  private String email;

  @NotNull(message = "password must not be empty")
  private String password;
}
