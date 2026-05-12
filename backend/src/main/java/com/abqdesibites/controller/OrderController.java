package com.abqdesibites.controller;

import com.abqdesibites.dto.OrderRequest;
import com.abqdesibites.model.AddOn;
import com.abqdesibites.repository.AddOnRepository;
import com.abqdesibites.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final AddOnRepository addOnRepository;

    @GetMapping("/addons")
    public List<AddOn> getAddOns() {
        return addOnRepository.findByIsAvailableTrue();
    }

    @PostMapping("/orders")
    public ResponseEntity<Map<String, Object>> createOrder(@Valid @RequestBody OrderRequest req) {
        try {
            return ResponseEntity.ok(orderService.createOrder(req));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body(Map.of("error", "Failed to create order: " + e.getMessage()));
        }
    }

    @PostMapping("/orders/confirm")
    public ResponseEntity<Void> confirmOrder(@RequestBody Map<String, String> body) {
        orderService.confirmOrder(body.get("paymentIntentId"));
        return ResponseEntity.ok().build();
    }
}
