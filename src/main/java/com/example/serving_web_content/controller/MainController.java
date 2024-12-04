package com.example.serving_web_content.controller;

import com.example.serving_web_content.Entity.Wallpaper;
import com.example.serving_web_content.repository.WallpaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private WallpaperRepository wallpaperRepository;

    @GetMapping("/main")
    public String mainPage(Model model) {
        Iterable<Wallpaper> wallpaper = wallpaperRepository.findAll();
        System.out.println("Найденные товары: " + wallpaper);
        model.addAttribute("wallpapers", wallpaper);
        return "mainPage";
    }

    @GetMapping("/addWallpaper")
    public String addItem(Model model) {
        return "addWallpaper";
    }

    @GetMapping("/pageWallpaper/{id}")
    public String itemPage(@PathVariable Long id, Model model) {
        // Находим товар по ID
        Optional<Wallpaper> optionalItem = wallpaperRepository.findById(id);

        // Проверяем, найден ли товар
        if (optionalItem.isPresent()) {
            Wallpaper wallpaper = optionalItem.get();
            model.addAttribute("wallpaper", wallpaper); // Добавляем товар в модель
        } else {
            System.out.println("Товар с ID " + id + " не найден.");
            model.addAttribute("error", "Товар не найден."); // Добавляем сообщение об ошибке
        }

        return "pageWallpaper"; // Возвращаем название HTML-шаблона
    }
}