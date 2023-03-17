package com.example.serverForCA.modules.user;

import com.example.serverForCA.constans.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Setter
@Getter
public class User  extends BaseEntity {
  @Column(name = "name")
  private String name;

  @Column(name = "age")
  private Integer age;

  @Column(name = "email")
  private String email;

  @Column(name = "password")
  private String password;
}
