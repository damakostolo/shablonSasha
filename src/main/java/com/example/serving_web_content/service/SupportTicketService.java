package com.example.serving_web_content.service;

import com.example.serving_web_content.Entity.SupportTicket;
import com.example.serving_web_content.repository.SupportTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupportTicketService {

    private final SupportTicketRepository supportTicketRepository;

    @Autowired
    public SupportTicketService(SupportTicketRepository supportTicketRepository) {
        this.supportTicketRepository = supportTicketRepository;
    }

    public List<SupportTicket> getAllTickets() {
        return supportTicketRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    public SupportTicket saveTicket(SupportTicket ticket) {
        return supportTicketRepository.save(ticket);
    }

    public void deleteTicket(Long id) {
        supportTicketRepository.deleteById(id);
    }

    public SupportTicket findById(Long id) {
        return supportTicketRepository.findById(id).orElse(null);
    }
}
