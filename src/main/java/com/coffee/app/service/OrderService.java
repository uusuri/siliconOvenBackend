package com.coffee.app.service;

import com.coffee.app.model.OrderItemRequest;
import com.coffee.app.model.OrderResponse;
import com.coffee.app.model.entities.Drink;
import com.coffee.app.model.entities.Order;
import com.coffee.app.model.entities.OrderItem;
import com.coffee.app.model.entities.User;
import com.coffee.app.repositiory.DrinkRepository;
import com.coffee.app.repositiory.OrderRepository;
import com.coffee.app.repositiory.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    private final DrinkRepository drinkRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public OrderService(DrinkRepository drinkRepository, OrderRepository orderRepository,
                        UserRepository userRepository) {
        this.drinkRepository = drinkRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    public Order createOrder(Long userId) {
        User managedUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Order order = new Order();
        order.setUser(managedUser);
        order.setOrderItems(new java.util.ArrayList<>());
        order.setTotalAmount(BigDecimal.ZERO);
        return orderRepository.save(order);
    }

    public OrderResponse createOrderWithItems(Long userId, List<OrderItemRequest> items) {
        User managedUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Order order = new Order();
        order.setUser(managedUser);
        order.setStatus("PENDING");
        order.setDate(LocalDateTime.now());

        List<OrderItem> orderItems = items.stream().map(itemRequest -> {
            Drink drink = drinkRepository.findById(itemRequest.getDrinkId())
                    .orElseThrow(() -> new RuntimeException("Drink not found with id: " + itemRequest.getDrinkId()));
            OrderItem orderItem = new OrderItem();
            orderItem.setDrink(drink);
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setOrder(order);
            orderItem.recalculateTotalPrice();
            return orderItem;
        }).toList();

        order.setOrderItems(orderItems);

        BigDecimal total = orderItems.stream()
                .map(OrderItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setTotalAmount(total);

        orderRepository.save(order);

        return new OrderResponse(order.getId(), order.getStatus(), order.getDate());
    }

    public Order addItemToOrder(Order order, Long drinkId, int quantity) {
        var drink = drinkRepository.findById(drinkId)
                .orElseThrow(() -> new IllegalArgumentException("Drink not found"));

        OrderItem item = new OrderItem();
        item.setDrink(drink);
        item.setQuantity(quantity);
        item.recalculateTotalPrice();
        item.setOrder(order);

        order.getOrderItems().add(item);
        recalculateOrderTotal(order);
        return orderRepository.save(order);
    }

    public Order updateItemQuantity(Order order, Long itemId, int quantity) {
        order.getOrderItems().stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst()
                .ifPresent(item -> {
                    item.setQuantity(quantity);
                    item.recalculateTotalPrice();
                });
        recalculateOrderTotal(order);
        return orderRepository.save(order);
    }

    public Order removeItemFromOrder(Order order, Long itemId) {
        order.getOrderItems().removeIf(item -> item.getId().equals(itemId));
        recalculateOrderTotal(order);
        return orderRepository.save(order);
    }

    private void recalculateOrderTotal(Order order) {
        BigDecimal total = order.getOrderItems().stream()
                .map(OrderItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setTotalAmount(total);
    }
}

