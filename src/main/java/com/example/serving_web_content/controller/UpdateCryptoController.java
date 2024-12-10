package com.example.serving_web_content.controller;

import com.example.serving_web_content.Entity.CryptoEntity;
import com.example.serving_web_content.service.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/cryptoUpdate")
public class UpdateCryptoController {

    private final CryptoService cryptoService;

    @Autowired
    public UpdateCryptoController(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }


    @PutMapping("/{id}")
    public ResponseEntity<CryptoEntity> updateCrypto(@PathVariable Long id, @RequestParam String name, @RequestParam String mark,@RequestParam Long price, @RequestParam String description, @RequestParam String img) {

        CryptoEntity cryptoEntity = cryptoService.findById(id);

            cryptoEntity.setName(name);
            cryptoEntity.setMark(mark);
            cryptoEntity.setPrice(price);
            cryptoEntity.setDescription(description);
            cryptoEntity.setImg(img);

        try {
            // Сохраняем книгу с помощью сервиса
            cryptoService.saveItem(cryptoEntity);


            // Возвращаем сохраненный объект
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create("/main"))
                .build();
    }
        catch (Exception e) {
            // Если произошла ошибка, логируем её и возвращаем 500 (Ошибка сервера)
            System.err.println("Ошибка при обновлении active: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }}