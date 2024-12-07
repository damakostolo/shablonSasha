package com.example.serving_web_content.controller;

import com.example.serving_web_content.Entity.CryptoEntity;
import com.example.serving_web_content.service.ServingWebContentApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/deleteCrypto")
public class DeleteCryptoController {

    private final ServingWebContentApplication servingWebContentApplication;

    @Autowired
    public DeleteCryptoController(ServingWebContentApplication servingWebContentApplication) {
        this.servingWebContentApplication = servingWebContentApplication;
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<CryptoEntity> updateBook(@PathVariable Long id) {

        try {
            servingWebContentApplication.deleteBook(id);
            return ResponseEntity.ok().build();

        }
        catch (Exception e) {
            // Если произошла ошибка, логируем её и возвращаем 500 (Ошибка сервера)
            System.err.println("Ошибка при удалении активу: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }}