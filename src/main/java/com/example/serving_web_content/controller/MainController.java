package com.example.serving_web_content.controller;

import com.example.serving_web_content.Entity.MaterialEntity;
import com.example.serving_web_content.repository.DBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private DBRepository DBRepository;

    @GetMapping("/main")
    public String mainPage(Model model) {
        Iterable<MaterialEntity> material = DBRepository.findAll();
        System.out.println("Найденные товары: " + material);
        model.addAttribute("materials", material);
        return "mainPage";
    }

    @GetMapping("/addMaterial")
    public String addItem(Model model) {
        return "addMaterial";
    }

    @GetMapping("/materialPage/{id}")
    public String itemPage(@PathVariable Long id, Model model) {
        // Находим товар по ID
        Optional<MaterialEntity> optionalItem = DBRepository.findById(id);

        // Проверяем, найден ли товар
        if (optionalItem.isPresent()) {
            MaterialEntity materialEntity = optionalItem.get();
            model.addAttribute("material", materialEntity); // Добавляем товар в модель
        } else {
            System.out.println("Товар с ID " + id + " не найден.");
            model.addAttribute("error", "Товар не найден."); // Добавляем сообщение об ошибке
        }

        return "materialPage"; // Возвращаем название HTML-шаблона
    }
}