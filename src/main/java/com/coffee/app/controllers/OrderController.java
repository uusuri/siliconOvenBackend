package com.coffee.app.controllers;

import com.coffee.app.model.OrderItemRequest;
import com.coffee.app.model.OrderResponse;
import com.coffee.app.model.entities.User;
import com.coffee.app.repositiory.UserRepository;
import com.coffee.app.service.OrderService;
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

    @PostMapping("/order")
    public OrderResponse createOrder(Authentication authentication, @RequestBody List<OrderItemRequest> items) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));

        return orderService.createOrderWithItems(user.getId(), items);
    }
}
