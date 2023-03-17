package com.example.serverForCA.modules.item;

import com.example.serverForCA.modules.item.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/items")
public class ItemRestControllerV1 {

  private ItemService itemService;

  @Autowired
  public ItemRestControllerV1(ItemService itemService) {
    this.itemService = itemService;
  }

  @GetMapping("/{itemId}")
  ResponseEntity getById(@PathVariable String itemId) {
    return new ResponseEntity("item", HttpStatus.OK);
  }

  @PostMapping()
  ResponseEntity createItem() {
    return new ResponseEntity("item", HttpStatus.OK);
  }

  @PutMapping("/{itemId}")
  ResponseEntity updateItem(@PathVariable String itemId) {
    return new ResponseEntity("item", HttpStatus.OK);
  }

  @DeleteMapping("/{itemId}")
  ResponseEntity deleteItem(@PathVariable String itemId) {
    return new ResponseEntity("item", HttpStatus.OK);
  }
}
