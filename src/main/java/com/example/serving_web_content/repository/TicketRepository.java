package com.example.serving_web_content.repository;

import com.example.serving_web_content.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    long countByEventId(Long eventId);

    boolean existsByEventIdAndParticipantId(Long eventId, Long participantId);

    List<Ticket> findByEventIdOrderByPurchaseDateTimeAsc(Long eventId);
}
