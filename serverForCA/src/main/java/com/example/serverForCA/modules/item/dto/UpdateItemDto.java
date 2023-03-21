package com.example.serverForCA.modules.item.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateItemDto {
  private String name;

  private String description;

  private Integer count;
}
