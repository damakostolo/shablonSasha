package com.example.serving_web_content.repository;

import com.example.serving_web_content.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    Optional<Participant> findByEmailIgnoreCase(String email);
}
