package com.example.serving_web_content.service;

import com.example.serving_web_content.Entity.Book;
import com.example.serving_web_content.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public String storeCover(MultipartFile coverFile) throws IOException {
        if (coverFile == null || coverFile.isEmpty()) {
            throw new IllegalArgumentException("Файл обкладинки обов'язковий");
        }

        String contentType = coverFile.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new IllegalArgumentException("Дозволені лише файли зображень");
        }

        String extension = StringUtils.getFilenameExtension(coverFile.getOriginalFilename());
        if ((extension == null || extension.isBlank()) && contentType.contains("/")) {
            extension = contentType.substring(contentType.indexOf('/') + 1);
        }

        String sanitizedExtension = (extension == null || extension.isBlank()) ? "" : "." + extension;
        String fileName = UUID.randomUUID() + sanitizedExtension;

        Path uploadDir = Paths.get("uploads");
        Files.createDirectories(uploadDir);

        Path destination = uploadDir.resolve(fileName).normalize();
        Files.copy(coverFile.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);

        return "/uploads/" + fileName;
    }

    public Book findById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }
}
