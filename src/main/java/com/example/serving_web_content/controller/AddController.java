package com.example.serving_web_content.controller;

import com.example.serving_web_content.Entity.Item;
import com.example.serving_web_content.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/addItems")
public class AddController {

    private final ItemService itemService;

    @Autowired
    public AddController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<Item> addItem(@RequestParam String text, @RequestParam Long number) {
        // Создаем объект Item и заполняем данными
        Item item = new Item();
        item.setName(text);
        item.setAmount(number);

        // Сохраняем объект через сервис
        Item savedItem = itemService.saveItem(item);

        // Возвращаем сохраненный объект
        return ResponseEntity.ok(savedItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItemAmount(@PathVariable Long id, @RequestBody UpdateAmountRequest request) {
        // Получаем существующий товар
        Item item = itemService.findById(id);
        if (item == null) {
            return ResponseEntity.notFound().build();
        }

        // Обновляем количество
        item.setAmount(request.getAmount());
        Item updatedItem = itemService.saveItem(item);

        // Возвращаем обновленный товар
        return ResponseEntity.ok(updatedItem);
    }
}

// DTO для приема количества
class UpdateAmountRequest {
    private Long amount;

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

}