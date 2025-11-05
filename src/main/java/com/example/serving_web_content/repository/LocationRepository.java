package com.example.serving_web_content.repository;

import com.example.serving_web_content.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
