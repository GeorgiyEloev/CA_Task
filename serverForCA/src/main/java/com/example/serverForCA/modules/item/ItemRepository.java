package com.example.serverForCA.modules.item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
  Optional<Item> findByName(String name);

  Page<Item> findByNameContainingIgnoreCase(String name, Pageable pageable);
  void deleteById(Long id);
}
