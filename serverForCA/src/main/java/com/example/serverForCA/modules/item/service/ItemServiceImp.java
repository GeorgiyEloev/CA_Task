package com.example.serverForCA.modules.item.service;

import com.example.serverForCA.exceptions.ItemNotFoundException;
import com.example.serverForCA.modules.item.Item;
import com.example.serverForCA.modules.item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImp implements ItemService {
  private final ItemRepository itemRepository;

  @Autowired
  public ItemServiceImp(ItemRepository itemRepository) {
    this.itemRepository = itemRepository;
  }

  @Override
  public Item getById(Long id) {
    return itemRepository
            .findById(id)
            .orElseThrow(() -> new ItemNotFoundException("Item not found!"));
  }

  @Override
  public Item createItem(Item item) {
    return itemRepository
            .save(item);
  }

  @Override
  public Item updateItem(Item item) {
    Item oldItem = getById(item.getId());

    oldItem.setCount(item.getCount());
    oldItem.setName(item.getName());
    oldItem.setDescription(item.getDescription());

    return itemRepository
            .save(oldItem);
  }

  @Override
  public void deleteItem(Long id) {
    itemRepository.deleteById(id);
  }
}
