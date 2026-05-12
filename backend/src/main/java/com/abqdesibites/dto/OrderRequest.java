package com.abqdesibites.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class OrderRequest {
    @NotBlank private String customerName;
    @NotBlank @Email private String customerEmail;
    private String customerPhone;
    private String pickupTime;
    private String specialInstructions;
    @NotEmpty private List<OrderItemRequest> items;
}
