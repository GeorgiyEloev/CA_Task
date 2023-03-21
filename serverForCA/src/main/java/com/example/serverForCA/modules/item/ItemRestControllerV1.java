package com.example.serverForCA.modules.item;

import com.example.serverForCA.modules.item.dto.CreateItemDto;
import com.example.serverForCA.modules.item.dto.UpdateItemDto;
import com.example.serverForCA.modules.item.service.ItemService;
import com.example.serverForCA.utils.constans.Response;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/items")
public class ItemRestControllerV1 {

  private ItemService itemService;

  @Autowired
  public ItemRestControllerV1(ItemService itemService) {
    this.itemService = itemService;
  }

  @Operation(summary = "Get all item or find items")
  @GetMapping()
  ResponseEntity<List<Item>> getAll(@RequestParam(defaultValue = "0") Integer page,
                                     @RequestParam(defaultValue = "10") Integer size,
                                     @RequestParam(defaultValue = "") String name) {
    List<Item> items = itemService.getAllOrFindAll(page, size, name);

    if (items.isEmpty()) {
      return new ResponseEntity<>(new ArrayList<>(),HttpStatus.NO_CONTENT);
    }

    return new ResponseEntity<>(items, HttpStatus.OK);
  }

  @Operation(summary = "Get one item")
  @GetMapping("/{itemId}")
  ResponseEntity<Item> getById(@PathVariable Long itemId) {
    Item item = itemService.getById(itemId);
    return new ResponseEntity<>(item, HttpStatus.OK);
  }


  @Operation(summary = "Create item")
  @PostMapping()
  ResponseEntity<Item> createItem(@RequestBody @Valid CreateItemDto createItemDto) {
    Item item = itemService.createItem(createItemDto);
    return new ResponseEntity<>(item, HttpStatus.OK);
  }

  @Operation(summary = "Update item")
  @PutMapping("/{itemId}")
  ResponseEntity<Item> updateItem(@PathVariable Long itemId, @RequestBody UpdateItemDto updateDto) {
    Item item = itemService.updateItem(itemId, updateDto);
    return new ResponseEntity<>(item, HttpStatus.OK);
  }

  @Operation(summary = "Delete item")
  @DeleteMapping("/{itemId}")
  ResponseEntity<Response> deleteItem(@PathVariable Long itemId) {
    itemService.deleteItem(itemId);
    return new ResponseEntity<>(new Response("Delete is successful"), HttpStatus.OK);
  }
}
