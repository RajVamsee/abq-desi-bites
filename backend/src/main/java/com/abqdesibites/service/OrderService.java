package com.abqdesibites.service;

import com.abqdesibites.dto.OrderRequest;
import com.abqdesibites.model.Order;
import com.abqdesibites.model.OrderItem;
import com.abqdesibites.repository.OrderRepository;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final EmailService emailService;

    @Value("${stripe.secret-key}")
    private String stripeSecretKey;

    public Map<String, Object> createOrder(OrderRequest req) throws StripeException {
        com.stripe.Stripe.apiKey = stripeSecretKey;

        double subtotal = req.getItems().stream().mapToDouble(item -> {
            double itemTotal = item.getUnitPrice() * item.getQuantity();
            if (item.getAddOnPrice() != null) itemTotal += item.getAddOnPrice() * item.getQuantity();
            return itemTotal;
        }).sum();

        double total = Math.round(subtotal * 100.0) / 100.0;
        long amountInCents = Math.round(total * 100);

        PaymentIntent intent = PaymentIntent.create(
            PaymentIntentCreateParams.builder()
                .setAmount(amountInCents)
                .setCurrency("usd")
                .putMetadata("customerName", req.getCustomerName())
                .putMetadata("customerEmail", req.getCustomerEmail())
                .build()
        );

        Order order = new Order();
        order.setCustomerName(req.getCustomerName());
        order.setCustomerEmail(req.getCustomerEmail());
        order.setCustomerPhone(req.getCustomerPhone());
        order.setPickupTime(req.getPickupTime());
        order.setSpecialInstructions(req.getSpecialInstructions());
        order.setSubtotal(subtotal);
        order.setTotalAmount(total);
        order.setStripePaymentIntentId(intent.getId());
        order.setStatus("PENDING");

        List<OrderItem> items = req.getItems().stream().map(r -> {
            OrderItem oi = new OrderItem();
            oi.setOrder(order);
            oi.setMenuItemId(r.getMenuItemId());
            oi.setItemName(r.getItemName());
            oi.setUnitPrice(r.getUnitPrice());
            oi.setQuantity(r.getQuantity());
            oi.setAddOnId(r.getAddOnId());
            oi.setAddOnName(r.getAddOnName());
            oi.setAddOnPrice(r.getAddOnPrice());
            return oi;
        }).toList();

        order.setItems(items);
        orderRepository.save(order);

        return Map.of(
            "clientSecret", intent.getClientSecret(),
            "orderId", order.getId(),
            "total", total
        );
    }

    public void confirmOrder(String paymentIntentId) {
        Order order = orderRepository.findByStripePaymentIntentId(paymentIntentId);
        if (order != null) {
            order.setStatus("PAID");
            orderRepository.save(order);
            emailService.sendOrderConfirmation(order);
        }
    }
}
