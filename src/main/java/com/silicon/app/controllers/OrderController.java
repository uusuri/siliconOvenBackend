package com.silicon.app.controllers;

import com.silicon.app.models.entities.Order;
import com.silicon.app.models.requests.OrderItemRequest;
import com.silicon.app.repositories.UserRepository;
import com.silicon.app.services.OrderService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {
    private final UserRepository userRepository;
    private final OrderService orderService;

    public OrderController(UserRepository userRepository, OrderService orderService) {
        this.userRepository = userRepository;
        this.orderService = orderService;
    }

    @PostMapping("/user/order")
    public Order createOrder(Authentication authentication, @RequestBody List<OrderItemRequest> items) {
        Long userId = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new EntityNotFoundException("User not found"))
                .getId();

        return orderService.createOrder(userId, items);
    }
}
