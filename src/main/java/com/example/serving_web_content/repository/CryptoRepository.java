package com.example.serving_web_content.repository;

import com.example.serving_web_content.Entity.CryptoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CryptoRepository extends JpaRepository<CryptoEntity, Long> {
}
