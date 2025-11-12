package com.example.serving_web_content.controller;

import com.example.serving_web_content.Entity.SupportTicket;
import com.example.serving_web_content.service.SupportTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/tickets")
public class TicketApiController {

    private final SupportTicketService supportTicketService;

    @Autowired
    public TicketApiController(SupportTicketService supportTicketService) {
        this.supportTicketService = supportTicketService;
    }

    @PostMapping
    public ResponseEntity<Void> createTicket(@RequestParam String title,
                                             @RequestParam String requesterName,
                                             @RequestParam String requesterEmail,
                                             @RequestParam String description,
                                             @RequestParam(required = false) String attachmentUrl) {
        SupportTicket ticket = new SupportTicket();
        ticket.setTitle(title.trim());
        ticket.setRequesterName(requesterName.trim());
        ticket.setRequesterEmail(requesterEmail.trim());
        ticket.setDescription(description.trim());
        if (attachmentUrl != null && !attachmentUrl.isBlank()) {
            ticket.setAttachmentUrl(attachmentUrl.trim());
        }
        ticket.setStatus("Нова заявка");

        supportTicketService.saveTicket(ticket);

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create("/tickets"))
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupportTicket> updateTicket(@PathVariable Long id, @RequestBody UpdateTicketRequest request) {
        SupportTicket ticket = supportTicketService.findById(id);
        if (ticket == null) {
            return ResponseEntity.notFound().build();
        }

        if (request.getStatus() != null && !request.getStatus().isBlank()) {
            ticket.setStatus(request.getStatus().trim());
        }

        if (request.getAssignedTo() != null) {
            String assignedTo = request.getAssignedTo().trim();
            ticket.setAssignedTo(assignedTo.isEmpty() ? null : assignedTo);
        }

        SupportTicket updatedTicket = supportTicketService.saveTicket(ticket);
        return ResponseEntity.ok(updatedTicket);
    }

    private static class UpdateTicketRequest {
        private String status;
        private String assignedTo;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getAssignedTo() {
            return assignedTo;
        }

        public void setAssignedTo(String assignedTo) {
            this.assignedTo = assignedTo;
        }
    }
}
