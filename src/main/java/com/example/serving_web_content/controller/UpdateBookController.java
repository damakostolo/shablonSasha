package com.example.serving_web_content.controller;

import com.example.serving_web_content.Entity.CryptoEntity;
import com.example.serving_web_content.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/bookUpdate")
public class UpdateBookController {

    private final LibraryService libraryService;

    @Autowired
    public UpdateBookController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }


    @PutMapping("/{id}")
    public ResponseEntity<CryptoEntity> updateBook(@PathVariable Long id, @RequestParam String title, @RequestParam String author, @RequestParam String description, @RequestParam String img) {

        CryptoEntity existingBook = libraryService.findById(id);

        // Проверяем, указаны ли обязательные параметры title и author
        if (title == null || title.trim().isEmpty() || author == null || author.trim().isEmpty()) {
            // Если что-то не указано, возвращаем статус 400 (Некорректный запрос)
            return ResponseEntity.badRequest().build();
        }

        // Создаем объект книги и заполняем его данными
        existingBook.setTitle(title.trim()); // trim убирает лишние пробелы
        existingBook.setAuthor(author.trim());
        existingBook.setDescription(description);
        existingBook.setImg(img); // Аналогично с изображением

        try {
            // Сохраняем книгу с помощью сервиса
            libraryService.saveItem(existingBook);


            // Возвращаем сохраненный объект
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create("/main"))
                .build();
    }
        catch (Exception e) {
            // Если произошла ошибка, логируем её и возвращаем 500 (Ошибка сервера)
            System.err.println("Ошибка при обновлении книги: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }}