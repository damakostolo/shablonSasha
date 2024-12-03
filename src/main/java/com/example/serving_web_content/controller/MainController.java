package com.example.serving_web_content.controller;

import com.example.serving_web_content.Entity.Item;
import com.example.serving_web_content.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/main")
    public String mainPage(Model model) {
        Iterable<Item> items = itemRepository.findAll();
        System.out.println("Найденные товары: " + items);
        model.addAttribute("items", items);
        return "mainPage";
    }

    @GetMapping("/addItem")
    public String addItem(Model model) {
        return "addItem";
    }

    @GetMapping("/itemPage/{id}")
    public String itemPage(@PathVariable Long id, Model model) {
        // Находим товар по ID
        Optional<Item> optionalItem = itemRepository.findById(id);

        // Проверяем, найден ли товар
        if (optionalItem.isPresent()) {
            Item item = optionalItem.get();
            model.addAttribute("item", item); // Добавляем товар в модель
        } else {
            System.out.println("Товар с ID " + id + " не найден.");
            model.addAttribute("error", "Товар не найден."); // Добавляем сообщение об ошибке
        }

        return "itemPage"; // Возвращаем название HTML-шаблона
    }
}