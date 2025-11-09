package com.example.serving_web_content.controller;

import com.example.serving_web_content.dto.EventForm;
import com.example.serving_web_content.dto.RegistrationForm;
import com.example.serving_web_content.entity.Event;
import com.example.serving_web_content.entity.Location;
import com.example.serving_web_content.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping({"/", "/events"})
    public String listEvents(Model model) {
        List<Event> events = eventService.getAllEvents();
        model.addAttribute("events", events);
        return "mainPage";
    }

    @GetMapping("/events/new")
    public String showCreateForm(Model model) {
        if (!model.containsAttribute("eventForm")) {
            EventForm form = new EventForm();
            model.addAttribute("eventForm", form);
        }
        List<Location> locations = eventService.getLocations();
        model.addAttribute("locations", locations);
        return "addEvent";
    }

    @PostMapping("/events")
    public String createEvent(@ModelAttribute("eventForm") EventForm form,
                              RedirectAttributes redirectAttributes) {
        try {
            Event event = eventService.createEvent(form);
            redirectAttributes.addFlashAttribute("successMessage", "Подію успішно створено");
            return "redirect:/events/" + event.getId();
        } catch (IllegalArgumentException ex) {
            redirectAttributes.addFlashAttribute("eventForm", form);
            redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
            return "redirect:/events/new";
        }
    }

    @GetMapping("/events/{id}")
    public String eventDetails(@PathVariable Long id, Model model) {
        Event event = eventService.getEvent(id);
        model.addAttribute("event", event);
        model.addAttribute("tickets", event.getTickets());
        int remainingSeats = Math.max(event.getCapacity() - event.getRegisteredParticipantsCount(), 0);
        model.addAttribute("remainingSeats", remainingSeats);
        model.addAttribute("registrationForm", new RegistrationForm());
        return "eventDetails";
    }

    @PostMapping("/events/{id}/register")
    public String registerForEvent(@PathVariable Long id,
                                   @ModelAttribute("registrationForm") RegistrationForm form,
                                   RedirectAttributes redirectAttributes) {
        try {
            eventService.registerParticipant(id, form);
            redirectAttributes.addFlashAttribute("successMessage", "Учасника зареєстровано успішно");
        } catch (IllegalArgumentException | IllegalStateException ex) {
            redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        }
        return "redirect:/events/" + id;
    }
}
