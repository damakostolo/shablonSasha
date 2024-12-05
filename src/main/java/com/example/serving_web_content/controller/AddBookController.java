package com.example.serving_web_content.controller;

import com.example.serving_web_content.Entity.CryptoEntity;
import com.example.serving_web_content.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/addBook")
public class AddBookController {

    private final LibraryService libraryService;

    @Autowired
    public AddBookController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @PostMapping // Додавання книги
    public ResponseEntity<CryptoEntity> addItem(@RequestParam String title, @RequestParam String author, @RequestParam String description, @RequestParam String img) {
        // Создаем объект Auto и заполняем данными
        CryptoEntity cryptoEntity = new CryptoEntity();
        cryptoEntity.setTitle(title);
        cryptoEntity.setAuthor(author);
        cryptoEntity.setDescription(description);
        cryptoEntity.setImg(img);

        // Сохраняем объект через сервис
        CryptoEntity savedCryptoEntity = libraryService.saveItem(cryptoEntity);

        // Возвращаем сохраненный объект
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create("/main"))
                .build();
    }}