package com.example.serving_web_content.controller;

import com.example.serving_web_content.Entity.BookEntity;
import com.example.serving_web_content.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/deleteBook")
public class DeleteController {

    private final LibraryService libraryService;

    @Autowired
    public DeleteController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<BookEntity> updateBook( @PathVariable Long id) {

        try {
            libraryService.deleteBook(id);
            return ResponseEntity.ok().build();

        }
        catch (Exception e) {
            // Если произошла ошибка, логируем её и возвращаем 500 (Ошибка сервера)
            System.err.println("Ошибка при удалении книги: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }}