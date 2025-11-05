package com.example.serving_web_content.controller;

import com.example.serving_web_content.Entity.ServiceRequest;
import com.example.serving_web_content.service.ServiceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/requests")
public class RequestCreateController {

    private final ServiceRequestService serviceRequestService;

    @Autowired
    public RequestCreateController(ServiceRequestService serviceRequestService) {
        this.serviceRequestService = serviceRequestService;
    }

    @PostMapping
    public ResponseEntity<Void> addRequest(@RequestParam String clientName,
                                           @RequestParam String contactInfo,
                                           @RequestParam String deviceType,
                                           @RequestParam String deviceModel,
                                           @RequestParam String issueDescription,
                                           @RequestParam String status) {
        ServiceRequest request = new ServiceRequest();
        request.setClientName(clientName);
        request.setContactInfo(contactInfo);
        request.setDeviceType(deviceType);
        request.setDeviceModel(deviceModel);
        request.setIssueDescription(issueDescription);
        request.setStatus(status);

        serviceRequestService.save(request);

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create("/requests"))
                .build();
    }
}
