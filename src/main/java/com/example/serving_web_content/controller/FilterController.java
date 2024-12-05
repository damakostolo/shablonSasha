package com.example.serving_web_content.controller;

import com.example.serving_web_content.Entity.BookEntity;
import com.example.serving_web_content.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@RequestMapping("/api/filterBook")
public class FilterController {

    private final LibraryService libraryService;

    @Autowired
    public FilterController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @PostMapping // Фильтрация книг
    public String filterBook(@RequestParam String title, Model model) {
        List<BookEntity> filteredBooks = libraryService.filterBooksByTitle(title);
        model.addAttribute("books", filteredBooks);
        return "mainPage";
    }

}

}