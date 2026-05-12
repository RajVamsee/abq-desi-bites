package com.abqdesibites.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderItemRequest {
    private Integer menuItemId;
    private String itemName;
    private Double unitPrice;
    private Integer quantity;
    private Integer addOnId;
    private String addOnName;
    private Double addOnPrice;
}
