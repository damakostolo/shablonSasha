package com.example.serving_web_content.controller;

import com.example.serving_web_content.Entity.CryptoEntity;
import com.example.serving_web_content.repository.CryptoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class GetController {

    @Autowired
    private CryptoRepository cryptoRepository;

    @GetMapping("/main") // Получаем главную страницу
    public String mainPage(Model model) {
        Iterable<CryptoEntity> cryptos = cryptoRepository.findAll();
        System.out.println("Найденные товары: " + cryptos);
        model.addAttribute("cryptos", cryptos);
        return "mainPage";
    }

    @GetMapping("/addCrypto") // Получаем страницу добавления книги
    public String addCrypto() {
        return "addCrypto";
    }


    @GetMapping("/pageCrypto/{id}") // Получаем страницу книги и её описания
    public String pageCrypto(@PathVariable Long id, Model model) {
        // Находим товар по ID
        Optional<CryptoEntity> optionalCrypto = cryptoRepository.findById(id);

        // Проверяем, найден ли товар
        if (optionalCrypto.isPresent()) {
            CryptoEntity crypto = optionalCrypto.get();
            model.addAttribute("crypto", crypto); // Добавляем товар в модель
        } else {
            System.out.println("Товар с ID " + id + " не найден.");
            model.addAttribute("error", "Товар не найден."); // Добавляем сообщение об ошибке
        }

        return "pageCrypto"; // Возвращаем название HTML-шаблона
    }

    @GetMapping("/updateCrypto/{id}") // Получаем страницу книги и её описания
    public String updateCrypto(@PathVariable Long id, Model model) {
        // Находим товар по ID
        Optional<CryptoEntity> optionalCrypto = cryptoRepository.findById(id);

        // Проверяем, найден ли товар
        if (optionalCrypto.isPresent()) {
            CryptoEntity crypto = optionalCrypto.get();
            model.addAttribute("crypto", crypto); // Добавляем товар в модель
        } else {
            System.out.println("Товар с ID " + id + " не найден.");
            model.addAttribute("error", "Товар не найден."); // Добавляем сообщение об ошибке
        }

        return "updateCrypto"; // Возвращаем название HTML-шаблона
    }
}