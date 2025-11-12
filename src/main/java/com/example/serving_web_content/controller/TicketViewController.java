package com.example.serving_web_content.controller;

import com.example.serving_web_content.Entity.SupportTicket;
import com.example.serving_web_content.service.SupportTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TicketViewController {

    private final SupportTicketService supportTicketService;

    @Autowired
    public TicketViewController(SupportTicketService supportTicketService) {
        this.supportTicketService = supportTicketService;
    }

    @GetMapping("/")
    public String rootRedirect() {
        return "redirect:/tickets";
    }

    @GetMapping("/tickets")
    public String tickets(Model model) {
        model.addAttribute("tickets", supportTicketService.getAllTickets());
        return "tickets";
    }

    @GetMapping("/tickets/new")
    public String createTicket(Model model) {
        return "createTicket";
    }

    @GetMapping("/tickets/{id}")
    public String ticketDetails(@PathVariable Long id, Model model) {
        SupportTicket ticket = supportTicketService.findById(id);
        if (ticket == null) {
            model.addAttribute("error", "Заявку не знайдено");
            return "ticketDetails";
        }
        model.addAttribute("ticket", ticket);
        return "ticketDetails";
    }
}
