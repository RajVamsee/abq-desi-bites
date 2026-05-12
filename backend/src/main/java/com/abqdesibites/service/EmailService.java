package com.abqdesibites.service;

import com.abqdesibites.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${app.notification-email}")
    private String notificationEmail;

    public void sendOrderConfirmation(Order order) {
        String subject = "Order Confirmed – ABQ Desi Bites #" + order.getId();
        String body = buildBody(order);

        send(order.getCustomerEmail(), subject, body);
        send(notificationEmail, "New Order #" + order.getId() + " – " + order.getCustomerName(), body);
    }

    private void send(String to, String subject, String body) {
        try {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(to);
            msg.setSubject(subject);
            msg.setText(body);
            mailSender.send(msg);
        } catch (Exception e) {
            // log but don't fail the order if email fails
        }
    }

    private String buildBody(Order order) {
        StringBuilder sb = new StringBuilder();
        sb.append("Hi ").append(order.getCustomerName()).append(",\n\n");
        sb.append("Your order has been confirmed! Here's your summary:\n\n");
        order.getItems().forEach(item -> {
            sb.append("• ").append(item.getItemName())
              .append(" x").append(item.getQuantity())
              .append(" – $").append(String.format("%.2f", item.getUnitPrice() * item.getQuantity()));
            if (item.getAddOnName() != null) {
                sb.append(" + ").append(item.getAddOnName());
            }
            sb.append("\n");
        });
        sb.append("\nTotal: $").append(String.format("%.2f", order.getTotalAmount()));
        if (order.getPickupTime() != null) {
            sb.append("\nPickup time: ").append(order.getPickupTime());
        }
        sb.append("\n\nAddress: 1800 Lomas Blvd NE, Albuquerque, NM 87112");
        sb.append("\nPhone: (505) 677-4394");
        sb.append("\n\nThank you for ordering from ABQ Desi Bites!");
        return sb.toString();
    }
}
