package com.abqdesibites.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order_items")
@Getter @Setter
public class OrderItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private Integer menuItemId;
    private String itemName;
    private Double unitPrice;
    private Integer quantity;
    private Integer addOnId;
    private String addOnName;
    private Double addOnPrice;
}
