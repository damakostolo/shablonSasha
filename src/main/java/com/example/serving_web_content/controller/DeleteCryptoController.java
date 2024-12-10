package com.example.serving_web_content.controller;

import com.example.serving_web_content.Entity.CryptoEntity;
import com.example.serving_web_content.service.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/deleteCrypto")
public class DeleteCryptoController {

    private final CryptoService cryptoService;

    @Autowired
    public DeleteCryptoController(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<CryptoEntity> deleteCrypto(@PathVariable Long id) {

        try {
            cryptoService.deleteBook(id);
            return ResponseEntity.ok().build();

        }
        catch (Exception e) {
            // Если произошла ошибка, логируем её и возвращаем 500 (Ошибка сервера)
            System.err.println("Ошибка при удалении активу: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }}