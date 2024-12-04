package com.example.serving_web_content.repository;

import com.example.serving_web_content.Entity.MaterialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DBRepository extends JpaRepository<MaterialEntity, Long> {
}