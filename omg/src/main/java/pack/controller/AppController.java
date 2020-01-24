package pack.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pack.dto.ItemAmountDto;
import pack.dto.ItemCreationDto;
import pack.dto.ItemDto;
import pack.service.ItemService;

import java.util.List;


@RequestMapping("item")
@RestController
public class AppController {

    private final ItemService itemService;

    @Autowired
    public AppController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping(path = "/items")
    public ResponseEntity<ItemDto> addItem(@RequestBody ItemCreationDto item) {
        return ResponseEntity.ok(itemService.addItem(item));
    }

    @GetMapping(path = "/items")
    public ResponseEntity<List<ItemDto>> getAllItems() {
        return ResponseEntity.ok(itemService.getAllItems());
    }

    @GetMapping(path = "/items/{id}")
    public ResponseEntity<ItemDto> getItemById(@PathVariable("id") int id) {
        ItemDto itemDto = itemService.getItemById(id);
        if (itemDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(itemDto);
    }

    @PutMapping(path = "/items/{id}/amount")
    public ResponseEntity<ItemDto> updateItemAmount(@PathVariable("id") int id, @RequestBody ItemAmountDto itemAmountDto) {
        ItemDto itemDto = itemService.updateItemAmount(id, itemAmountDto);
        if (itemDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(itemDto);
    }
}