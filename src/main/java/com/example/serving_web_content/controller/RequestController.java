package com.example.serving_web_content.controller;

import com.example.serving_web_content.Entity.ServiceRequest;
import com.example.serving_web_content.service.ServiceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class RequestController {

    private static final List<String> STATUSES = List.of(
            "Отримано",
            "Діагностика",
            "В ремонті",
            "Готово до видачі"
    );

    private final ServiceRequestService serviceRequestService;

    @Autowired
    public RequestController(ServiceRequestService serviceRequestService) {
        this.serviceRequestService = serviceRequestService;
    }

    @GetMapping({"/", "/requests"})
    public String mainPage(Model model) {
        List<ServiceRequest> requests = serviceRequestService.getAllRequests();
        model.addAttribute("requests", requests);
        return "mainPage";
    }

    @GetMapping("/requests/new")
    public String addRequest(Model model) {
        model.addAttribute("statuses", STATUSES);
        return "createRequest";
    }

    @GetMapping("/main")
    public String legacyMainRedirect() {
        return "redirect:/requests";
    }

    @GetMapping("/requests/{id}")
    public String requestDetails(@PathVariable Long id, Model model) {
        Optional<ServiceRequest> optionalRequest = serviceRequestService.findById(id);
        if (optionalRequest.isPresent()) {
            model.addAttribute("request", optionalRequest.get());
        } else {
            model.addAttribute("error", "Заявку з ID " + id + " не знайдено.");
        }
        return "requestDetails";
    }
}
