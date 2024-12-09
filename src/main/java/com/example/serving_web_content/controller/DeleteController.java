package com.example.serving_web_content.controller;

import com.example.serving_web_content.Entity.MaterialEntity;
import com.example.serving_web_content.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/materialPage/api")
public class DeleteController {

    private final MaterialService materialService;

    @Autowired
    public DeleteController(MaterialService materialService) {
        this.materialService = materialService;
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<MaterialEntity> deleteMaterial(@PathVariable Long id){
        // Получаем существующий товар
        MaterialEntity material = materialService.findById(id);
        if (material == null) {
            return ResponseEntity.notFound().build();
        }

        // Удаляем элемент
        materialService.deleteById(id);

        // Возвращаем статус "Нет содержимого"
        return ResponseEntity.noContent().build();
    }
}
