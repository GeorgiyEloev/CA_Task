package com.example.serverForCA.modules.auth.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationDto extends LoginDto {

  @NotNull(message = "name must not be empty")
  private String name;

  @NotNull(message = "age must not be empty")
  private Integer age;
}
