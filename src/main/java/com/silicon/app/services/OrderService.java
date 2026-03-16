package com.silicon.app.services;

import com.silicon.app.models.entities.*;
import com.silicon.app.models.requests.OrderItemRequest;
import com.silicon.app.models.entities.ProductType;
import com.silicon.app.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final DrinkRepository drinkRepository;
    private final BakeryRepository bakeryRepository;
    private final OrderRepository orderRepository;
    private final MilkRepository milkRepository;
    private final SyrupRepository syrupRepository;

    public OrderService(DrinkRepository drinkRepository, BakeryRepository bakeryRepository, OrderRepository orderRepository, MilkRepository milkRepository, SyrupRepository syrupRepository) {
        this.drinkRepository = drinkRepository;
        this.bakeryRepository = bakeryRepository;
        this.orderRepository = orderRepository;
        this.milkRepository = milkRepository;
        this.syrupRepository = syrupRepository;
    }

    private BigDecimal calculateTotalPrice(List<OrderItemRequest> orderItems) {
        BigDecimal total = BigDecimal.ZERO;
        for (OrderItemRequest item : orderItems) {
            if (item.getProductType() == ProductType.DRINK) {
                Drink drink = drinkRepository.findById(item.getId())
                        .orElseThrow(() -> new EntityNotFoundException("Drink not found"));
                total = total.add(drink.getBasePrice().multiply(BigDecimal.valueOf(item.getQuantity())));
                if (item.getMilkId() != null && !Objects.equals(drink.getMilk().getId(), item.getMilkId())) {
                    Milk milk = milkRepository.findById(item.getMilkId())
                            .orElseThrow(() -> new EntityNotFoundException("Milk not found"));
                    total = total.add(milk.getPrice());
                }
                if (item.getSyrupIds() != null && !item.getSyrupIds().isEmpty()) {
                    List<Syrup> syrups = syrupRepository.findAllById(item.getSyrupIds());
                    Set<Long> defaultIds = drink.getDefaultSyrups()
                            .stream()
                            .map(Syrup::getId)
                            .collect(Collectors.toSet());
                    for (Syrup syrup : syrups) {
                        if (!defaultIds.contains(syrup.getId())) {
                            total = total.add(syrup.getPrice());
                        }
                    }
                }
            }
            if (item.getProductType() == ProductType.BAKERY) {
                Bakery bakery = bakeryRepository.findById(item.getId())
                        .orElseThrow(() -> new EntityNotFoundException("Bakery not found"));
                total = total.add(bakery.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
            }
        }
        return total;
    }

    public Order createOrder(Long userId, List<OrderItemRequest> orderItems) {
        Order order = new Order();

        order.setUserId(userId);
        order.setStatus("PENDING");
        order.setDate(LocalDateTime.now());
        order.setTotalAmount(calculateTotalPrice(orderItems));
        return orderRepository.save(order);
    }
}
