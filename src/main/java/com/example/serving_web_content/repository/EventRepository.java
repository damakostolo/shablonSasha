package com.example.serving_web_content.repository;

import com.example.serving_web_content.entity.Event;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

    @EntityGraph(attributePaths = {"location"})
    List<Event> findAllByOrderByStartDateTimeAsc();

    @EntityGraph(attributePaths = {"location", "tickets", "tickets.participant"})
    Optional<Event> findDetailedById(Long id);
}
