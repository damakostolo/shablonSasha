package com.example.serving_web_content.controller;

import com.example.serving_web_content.service.ServiceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/requests")
public class RequestDeleteController {

    private final ServiceRequestService serviceRequestService;

    @Autowired
    public RequestDeleteController(ServiceRequestService serviceRequestService) {
        this.serviceRequestService = serviceRequestService;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
        if (serviceRequestService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        serviceRequestService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
