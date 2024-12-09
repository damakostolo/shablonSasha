package com.example.serving_web_content.controller;

import com.example.serving_web_content.Entity.MaterialEntity;
import com.example.serving_web_content.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/materialPage/api/addMaterial")
public class AddController {

    private final MaterialService materialService;

    @Autowired
    public AddController(MaterialService materialService) {
        this.materialService = materialService;
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
        MaterialEntity savedMaterialEntity = materialService.saveItem(materialEntity);

        // Возвращаем сохраненный объект
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create("/main"))
                .build();
    }

}
