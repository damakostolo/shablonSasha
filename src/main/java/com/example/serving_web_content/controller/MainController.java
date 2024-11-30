package com.example.serving_web_content.controller;

import com.example.serving_web_content.Entity.Item;
import com.example.serving_web_content.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}