package com.abqdesibites.controller;

import com.abqdesibites.dto.ContactRequestDto;
import com.abqdesibites.service.ContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/contact")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @PostMapping
    public ResponseEntity<Map<String, String>> submitInquiry(@Valid @RequestBody ContactRequestDto request) {
        contactService.saveInquiry(request);
        return ResponseEntity.ok(Map.of("message", "Thank you! We will get back to you soon."));
    }
}
