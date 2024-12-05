package com.example.serving_web_content.controller;

import com.example.serving_web_content.Entity.BookEntity;
import com.example.serving_web_content.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class GetController {

    @Autowired
    private LibraryRepository libraryRepository;

    @GetMapping("/main") // Получаем главную страницу
    public String mainPage(Model model) {
        Iterable<BookEntity> books = libraryRepository.findAll();
        System.out.println("Найденные товары: " + books);
        model.addAttribute("books", books);
        return "mainPage";
    }

    @GetMapping("/addBook") // Получаем страницу добавления книги
    public String addBook() {
        return "addBook";
    }

    @GetMapping("/pageBook/{id}") // Получаем страницу книги и её описания
    public String pageBook(@PathVariable Long id, Model model) {
        // Находим товар по ID
        Optional<BookEntity> optionalBook = libraryRepository.findById(id);

        // Проверяем, найден ли товар
        if (optionalBook.isPresent()) {
            BookEntity book = optionalBook.get();
            model.addAttribute("book", book); // Добавляем товар в модель
        } else {
            System.out.println("Товар с ID " + id + " не найден.");
            model.addAttribute("error", "Товар не найден."); // Добавляем сообщение об ошибке
        }

        return "pageBook"; // Возвращаем название HTML-шаблона
    }

    @GetMapping("/updateBook/{id}") // Получаем страницу книги и её описания
    public String updateBook(@PathVariable Long id, Model model) {
        // Находим товар по ID
        Optional<BookEntity> optionalBook = libraryRepository.findById(id);

        // Проверяем, найден ли товар
        if (optionalBook.isPresent()) {
            BookEntity book = optionalBook.get();
            model.addAttribute("book", book); // Добавляем товар в модель
        } else {
            System.out.println("Товар с ID " + id + " не найден.");
            model.addAttribute("error", "Товар не найден."); // Добавляем сообщение об ошибке
        }

        return "updateBook"; // Возвращаем название HTML-шаблона
    }
}