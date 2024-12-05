package com.example.serving_web_content.service;


import com.example.serving_web_content.Entity.CryptoEntity;
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

    public List<CryptoEntity> getAllItems() {
        return libraryRepository.findAll();
    }

    public CryptoEntity saveItem(CryptoEntity cryptoEntity) {
        return libraryRepository.save(cryptoEntity);
    }

    public void deleteBook(Long id) {
        libraryRepository.deleteById(id);
    }

    public CryptoEntity findById(Long id) {
        return libraryRepository.findById(id).orElse(null);
    }
}
