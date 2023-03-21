package com.example.serverForCA.modules.user.dto;

import com.example.serverForCA.modules.user.User;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
  private Long id;

  private String name;

  private Integer age;

  private String email;

  public UserDto(User user){
    this.id = user.getId();
    this.age = user.getAge();
    this.email = user.getEmail();
    this.name = user.getName();
  }
}
