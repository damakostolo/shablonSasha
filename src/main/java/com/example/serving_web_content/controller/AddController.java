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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        // Проверяем, существует ли элемент
        Item item = itemService.findById(id);
        System.out.print(id);
        System.out.print(item);
        if (item == null) {
            return ResponseEntity.notFound().build();
        }

        // Удаляем элемент
        itemService.deleteById(id);

        // Возвращаем статус "Нет содержимого"
        return ResponseEntity.noContent().build();
    }
}
