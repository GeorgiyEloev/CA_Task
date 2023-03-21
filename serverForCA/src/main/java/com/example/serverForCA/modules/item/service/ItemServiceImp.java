package com.example.serverForCA.modules.item.service;

import com.example.serverForCA.exceptions.ItemNotFoundException;
import com.example.serverForCA.modules.item.Item;
import com.example.serverForCA.modules.item.ItemRepository;
import com.example.serverForCA.modules.item.dto.CreateItemDto;
import com.example.serverForCA.modules.item.dto.UpdateItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImp implements ItemService {
  private final ItemRepository itemRepository;

  @Autowired
  public ItemServiceImp(ItemRepository itemRepository) {
    this.itemRepository = itemRepository;
  }

  @Override
  public List<Item> getAllOrFindAll(Integer page, Integer size, String name) {
    List<Item> items;
    Pageable paging = PageRequest.of(page, size);

    Page<Item> pageItems ;
    if(name.isEmpty()) {
      pageItems = itemRepository.findAll(paging);
    } else {
      pageItems = itemRepository.findByNameContainingIgnoreCase(name, paging);
    }

    items = pageItems.getContent();

    return items;
  }

  @Override
  public Item getById(Long id) {
    return itemRepository
            .findById(id)
            .orElseThrow(() -> new ItemNotFoundException("Item not found!"));
  }

  @Override
  public Item createItem(CreateItemDto createDto) {
    Item item = new Item(createDto.getName(), createDto.getDescription(), createDto.getCount());
    return itemRepository
            .save(item);
  }

  @Override
  public Item updateItem(Long id, UpdateItemDto updateItemDto) {
    Item oldItem = getById(id);

    oldItem.setCount(updateItemDto.getCount() == null ? oldItem.getCount() : updateItemDto.getCount());
    oldItem.setName(updateItemDto.getName() == null ? oldItem.getName() : updateItemDto.getName());
    oldItem.setDescription(updateItemDto.getDescription() == null ? oldItem.getDescription() : updateItemDto.getDescription());

    return itemRepository
            .save(oldItem);
  }

  @Override
  public void deleteItem(Long id) {
    getById(id);
    itemRepository.deleteById(id);
  }
}
