package com.example.serving_web_content.controller;

import com.example.serving_web_content.Entity.Book;
import com.example.serving_web_content.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<?> addBook(@RequestParam String title,
                                     @RequestParam String author,
                                     @RequestParam Integer year,
                                     @RequestParam String genres,
                                     @RequestParam String annotation,
                                     @RequestParam("cover") MultipartFile coverFile) {
        try {
            Book book = new Book();
            book.setTitle(title);
            book.setAuthor(author);
            book.setPublicationYear(year);
            book.setGenres(genres);
            book.setAnnotation(annotation);
            String coverUrl = bookService.storeCover(coverFile);
            book.setCoverUrl(coverUrl);

            bookService.saveBook(book);

            return ResponseEntity.status(HttpStatus.FOUND)
                    .location(URI.create("/main"))
                    .build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Не вдалося зберегти книгу: " + e.getMessage());
        }
    }
}
