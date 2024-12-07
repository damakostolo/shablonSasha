package com.example.serving_web_content.controller;

import com.example.serving_web_content.Entity.CryptoEntity;
import com.example.serving_web_content.service.ServingWebContentApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/addCrypto")
public class AddCryptoController {

    private final ServingWebContentApplication servingWebContentApplication;

    @Autowired
    public AddCryptoController(ServingWebContentApplication servingWebContentApplication) {
        this.servingWebContentApplication = servingWebContentApplication;
    }

    @PostMapping // Додавання книги
    public ResponseEntity<CryptoEntity> addItem(@RequestParam String name, @RequestParam String mark,@RequestParam Long price, @RequestParam String description, @RequestParam String img) {
        // Создаем объект Auto и заполняем данными
        CryptoEntity cryptoEntity = new CryptoEntity();
        cryptoEntity.setName(name);
        cryptoEntity.setMark(mark);
        cryptoEntity.setPrice(price);
        cryptoEntity.setDescription(description);
        cryptoEntity.setImg(img);

        // Сохраняем объект через сервис
        CryptoEntity savedCryptoEntity = servingWebContentApplication.saveItem(cryptoEntity);

        // Возвращаем сохраненный объект
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create("/main"))
                .build();
    }}