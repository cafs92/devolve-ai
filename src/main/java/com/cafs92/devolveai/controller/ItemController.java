package com.cafs92.devolveai.controller;

import com.cafs92.devolveai.model.Item;
import com.cafs92.devolveai.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itens")
public class ItemController {

    @Autowired
    ItemRepository itemRepository;

    @GetMapping
    public List<Item> getItems(){
        return itemRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id){
        return itemRepository.findById(id).map(item ->
                                                        ResponseEntity.ok().body(item))
                                                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Item addItem(@RequestBody Item item){
        return itemRepository.save(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@RequestBody Item item, @PathVariable Long id){
        return itemRepository.findById(id).map(i -> {
                                                    i.setDescription(item.getDescription());
                                                    i.setName(item.getName());
                                                    Item updatedItem = itemRepository.save(i);
                                                    return ResponseEntity.ok().body(updatedItem);
                                                    })
                                                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable Long id){
        return itemRepository.findById(id).map(item -> {itemRepository.deleteById(id);
                                                        return ResponseEntity.ok().build();
                                                        })
                                                .orElse(ResponseEntity.notFound().build());
    }
}
