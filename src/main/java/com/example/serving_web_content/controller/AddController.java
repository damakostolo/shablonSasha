package com.example.serving_web_content.controller;

import com.example.serving_web_content.Entity.MaterialEntity;
import com.example.serving_web_content.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/addMaterial")
public class AddController {

    private final ItemService itemService;

    @Autowired
    public AddController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<MaterialEntity> addItem(@RequestParam String name, @RequestParam Long number, @RequestParam String description, @RequestParam String img) {
        // Создаем объект Item и заполняем данными
        MaterialEntity materialEntity = new MaterialEntity();
        materialEntity.setName(name);
        materialEntity.setAmount(number);
        materialEntity.setDescription(description);
        materialEntity.setSrc(img);

        // Сохраняем объект через сервис
        MaterialEntity savedMaterialEntity = itemService.saveItem(materialEntity);

        // Возвращаем сохраненный объект
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create("/main"))
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        // Проверяем, существует ли элемент
        MaterialEntity materialEntity = itemService.findById(id);
        System.out.print(id);
        System.out.print(materialEntity);
        if (materialEntity == null) {
            return ResponseEntity.notFound().build();
        }

        // Удаляем элемент
        itemService.deleteById(id);

        // Возвращаем статус "Нет содержимого"
        return ResponseEntity.noContent().build();
    }
}
