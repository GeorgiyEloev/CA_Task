package com.example.serverForCA.modules.item.service;

import com.example.serverForCA.modules.item.Item;

public interface ItemService {
  Item getById(Long id);

  Item createItem(Item item);

  Item updateItem(Item item);

  void deleteItem(Long id);
}
