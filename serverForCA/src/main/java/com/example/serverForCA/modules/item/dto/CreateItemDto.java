package com.example.serverForCA.modules.item.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateItemDto {
  @NotNull(message = "name must not be empty")
  private String name;

  @NotNull(message = "description must not be empty")
  private String description;

  @NotNull(message = "count must not be empty")
  private Integer count;
}
