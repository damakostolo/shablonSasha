package com.example.serving_web_content.Entity;

import com.example.serving_web_content.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping({"/", "/main"})
    public String mainPage(Model model) {
        Iterable<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "mainPage";
    }

    @GetMapping("/addBook")
    public String addBook() {
        return "addBook";
    }

    @GetMapping("/books/{id}")
    public String bookPage(@PathVariable Long id, Model model) {
        Optional<Book> optionalBook = bookRepository.findById(id);

        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            model.addAttribute("book", book);
        } else {
            model.addAttribute("error", "Книгу не знайдено.");
        }

        return "bookPage";
    }
}
