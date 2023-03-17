package com.example.serverForCA.modules.item;

import com.example.serverForCA.constans.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "items")
@Setter
@Getter
public class Item extends BaseEntity {
  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "count")
  private Integer count;
}
