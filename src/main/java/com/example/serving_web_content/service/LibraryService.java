package com.example.serving_web_content.service;


import com.example.serving_web_content.Entity.BookEntity;
import com.example.serving_web_content.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {

    private final LibraryRepository libraryRepository;

    @Autowired
    public LibraryService(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    public List<BookEntity> getAllItems() {
        return libraryRepository.findAll();
    }

    public BookEntity saveItem(BookEntity bookEntity) {
        return libraryRepository.save(bookEntity);
    }

    public BookEntity deleteBook(Long id) {
        libraryRepository.deleteById(id);
        return null;
    }

    public BookEntity findById(Long id) {
        return libraryRepository.findById(id).orElse(null);
    }
}
