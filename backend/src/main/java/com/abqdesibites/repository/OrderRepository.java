package com.abqdesibites.repository;

import com.abqdesibites.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findByStripePaymentIntentId(String paymentIntentId);
}
