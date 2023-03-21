package com.example.serverForCA.modules.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateDTO {
  private String name;
  private Integer age;

  public UserUpdateDTO() {
  }

  public UserUpdateDTO(String name, Integer age) {
    this.name = name;
    this.age = age;
  }

}
