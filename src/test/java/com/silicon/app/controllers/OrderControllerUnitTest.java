//package com.silicon.app.controllers;
//
//import com.silicon.app.models.requests.OrderItemRequest;
//import com.silicon.app.models.entities.Role;
//import com.silicon.app.models.entities.Drink;
//import com.silicon.app.models.entities.Syrup;
//import com.silicon.app.models.entities.User;
//import com.silicon.app.repositories.DrinkRepository;
//import com.silicon.app.repositories.SyrupRepository;
//import com.silicon.app.repositories.UserRepository;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//
//@SpringBootTest
//public class OrderControllerUnitTest {
//    @Autowired
//    private WebApplicationContext webApplicationContext;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private DrinkRepository drinkRepository;
//
//    @Autowired
//    private SyrupRepository syrupRepository;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    private MockMvc mockMvc;
//
//    @BeforeEach
//    void setUp() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
//                .apply(springSecurity())
//                .build();
//        userRepository.deleteAll();
//        drinkRepository.deleteAll();
//
//        User testUser = new User();
//        testUser.setUsername("test");
//        testUser.setEmail("test@test.com");
//        testUser.setPassword("password");
//        testUser.setRole(Role.ROLE_USER);
//        userRepository.save(testUser);
//
//        Syrup testSyrup = new Syrup();
//        testSyrup.setName("Vanilla");
//        testSyrup.setPrice(BigDecimal.valueOf(20));
//        testSyrup.setDescription("Vanilla syrup");
//        syrupRepository.save(testSyrup);
//
//        Drink testDrink = new Drink();
//        testDrink.setName("Espresso");
//        testDrink.setBasePrice(BigDecimal.valueOf(100));
//        testDrink.setAdditionalSyrups(List.of(testSyrup));
//        drinkRepository.save(testDrink);
//    }
//
//    @Test
//    @WithMockUser(username = "test")
//    void shouldCreateOrder() throws Exception {
//        List<OrderItemRequest> items = List.of(
//                new OrderItemRequest(1L, 2)
//        );
//
//        mockMvc.perform(post("/order")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(items)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.status").value("PENDING"));
//    }
//}
