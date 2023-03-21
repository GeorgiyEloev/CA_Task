package com.example.serverForCA.modules.item.service;

import com.example.serverForCA.modules.item.Item;
import com.example.serverForCA.modules.item.dto.CreateItemDto;
import com.example.serverForCA.modules.item.dto.UpdateItemDto;

import java.util.List;

public interface ItemService {

  List<Item> getAllOrFindAll(Integer page, Integer size, String name);

  Item getById(Long id);

  Item createItem(CreateItemDto item);

  Item updateItem(Long id,UpdateItemDto updateDto);

  void deleteItem(Long id);
}
