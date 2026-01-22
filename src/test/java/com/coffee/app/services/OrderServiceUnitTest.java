package com.coffee.app.services;

import com.coffee.app.model.entities.Order;
import com.coffee.app.model.entities.User;
import com.coffee.app.repositiory.OrderRepository;
import com.coffee.app.repositiory.UserRepository;
import com.coffee.app.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceUnitTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    void shouldCreateOrder() {
        User testUser = new User();
        testUser.setId(1L);
        testUser.setUsername("test@test.com");
        testUser.setEmail("test@test.com");
        testUser.setPassword("password");

        Order savedOrder = new Order();
        savedOrder.setId(1L);
        savedOrder.setUser(testUser);
        savedOrder.setTotalAmount(BigDecimal.ZERO);
        savedOrder.setOrderItems(new java.util.ArrayList<>());

        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));
        when(orderRepository.save(any(Order.class))).thenReturn(savedOrder);

        Order order = orderService.createOrder(1L);

        assertThat(order).isNotNull();
        assertThat(order.getId()).isEqualTo(1L);
        assertThat(order.getUser().getId()).isEqualTo(1L);
        assertThat(order.getTotalAmount()).isEqualTo(BigDecimal.ZERO);
        assertThat(order.getOrderItems()).isEmpty();
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        when(userRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> orderService.createOrder(999L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("User not found");
    }
}
