package com.example.serving_web_content.repository;

import com.example.serving_web_content.Entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepository extends JpaRepository<BookEntity, Long> {
}