package com.silicon.app.controllers;

import com.silicon.app.models.entities.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.silicon.app.models.requests.DrinkCreateRequest;
import com.silicon.app.services.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
public class AdminControllerUnitTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private AdminService adminService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
        reset(adminService);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void adminShouldCreateDrink() throws Exception {
        DrinkCreateRequest request = new DrinkCreateRequest();
        request.setName("Test Drink");
        request.setImage("test-drink.jpg");
        request.setBasePrice(BigDecimal.valueOf(100));
        request.setDrinkBaseId(1L);
        request.setMilkId(1L);
        request.setDefaultSyrupIds(List.of(1L));

        Drink mockDrink = new Drink();
        mockDrink.setId(1L);
        mockDrink.setName("Test Drink");
        mockDrink.setImage("test-drink.jpg");
        mockDrink.setBasePrice(BigDecimal.valueOf(100));
        mockDrink.setDefaultSyrups(new ArrayList<>());
        mockDrink.setAdditionalSyrups(new ArrayList<>());

        when(adminService.createDrink(any(DrinkCreateRequest.class))).thenReturn(mockDrink);

        mockMvc.perform(post("/admin/add/drink")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Test Drink"))
                .andExpect(jsonPath("$.image").value("test-drink.jpg"))
                .andExpect(jsonPath("$.basePrice").value(100));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void adminShouldCreateBakery() throws Exception {
        Bakery bakery = new Bakery();
        bakery.setName("Test Bakery");
        bakery.setImage("test.jpg");
        bakery.setPrice(BigDecimal.valueOf(2.99));
        bakery.setDescription("Test description");

        Bakery mockBakery = new Bakery();
        mockBakery.setId(1L);
        mockBakery.setName("Test Bakery");
        mockBakery.setImage("test.jpg");
        mockBakery.setPrice(BigDecimal.valueOf(2.99));
        mockBakery.setDescription("Test description");

        when(adminService.createBakery(any(Bakery.class))).thenReturn(mockBakery);

        mockMvc.perform(post("/admin/add/bakery")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(bakery)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Test Bakery"))
                .andExpect(jsonPath("$.image").value("test.jpg"))
                .andExpect(jsonPath("$.price").value(2.99))
                .andExpect(jsonPath("$.description").value("Test description"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void adminShouldCreateDrinkBase() throws Exception {
        DrinkBase drinkBase = new DrinkBase();
        drinkBase.setName("Test Base");
        drinkBase.setPrice(BigDecimal.valueOf(1.99));
        drinkBase.setDescription("Test description");

        DrinkBase mockDrinkBase = new DrinkBase();
        mockDrinkBase.setId(1L);
        mockDrinkBase.setName("Test Base");
        mockDrinkBase.setPrice(BigDecimal.valueOf(1.99));
        mockDrinkBase.setDescription("Test description");
        mockDrinkBase.setIsDefault(true);

        when(adminService.createDrinkBase(any(DrinkBase.class))).thenReturn(mockDrinkBase);

        mockMvc.perform(post("/admin/add/coffee")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(drinkBase)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Test Base"))
                .andExpect(jsonPath("$.price").value(1.99))
                .andExpect(jsonPath("$.description").value("Test description"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void adminShouldCreateMilk() throws Exception {
        Milk milk = new Milk();
        milk.setName("Test Milk");
        milk.setPrice(BigDecimal.valueOf(0.99));
        milk.setDescription("Test description");

        Milk mockMilk = new Milk();
        mockMilk.setId(1L);
        mockMilk.setName("Test Milk");
        mockMilk.setPrice(BigDecimal.valueOf(0.99));
        mockMilk.setDescription("Test description");

        when(adminService.createMilk(any(Milk.class))).thenReturn(mockMilk);

        mockMvc.perform(post("/admin/add/milk")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(milk)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Test Milk"))
                .andExpect(jsonPath("$.price").value(0.99))
                .andExpect(jsonPath("$.description").value("Test description"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void adminShouldCreateSyrup() throws Exception {
        Syrup syrup = new Syrup();
        syrup.setName("Test Syrup");
        syrup.setPrice(BigDecimal.valueOf(0.50));
        syrup.setDescription("Test description");

        Syrup mockSyrup = new Syrup();
        mockSyrup.setId(1L);
        mockSyrup.setName("Test Syrup");
        mockSyrup.setPrice(BigDecimal.valueOf(0.50));
        mockSyrup.setDescription("Test description");

        when(adminService.createSyrup(any(Syrup.class))).thenReturn(mockSyrup);

        mockMvc.perform(post("/admin/add/syrup")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(syrup)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Test Syrup"))
                .andExpect(jsonPath("$.price").value(0.50))
                .andExpect(jsonPath("$.description").value("Test description"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void adminShouldCreateDrinkWithoutSyrups() throws Exception {
        DrinkCreateRequest request = new DrinkCreateRequest();
        request.setName("Simple Latte");
        request.setImage("simple-latte.jpg");
        request.setBasePrice(BigDecimal.valueOf(150));
        request.setDrinkBaseId(1L);
        request.setMilkId(1L);

        Drink mockDrink = new Drink();
        mockDrink.setId(2L);
        mockDrink.setName("Simple Latte");
        mockDrink.setImage("simple-latte.jpg");
        mockDrink.setBasePrice(BigDecimal.valueOf(150));
        mockDrink.setDefaultSyrups(new ArrayList<>());
        mockDrink.setAdditionalSyrups(new ArrayList<>());

        when(adminService.createDrink(any(DrinkCreateRequest.class))).thenReturn(mockDrink);

        mockMvc.perform(post("/admin/add/drink")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Simple Latte"))
                .andExpect(jsonPath("$.basePrice").value(150));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void adminShouldCreateDrinkWithoutMilk() throws Exception {
        DrinkCreateRequest request = new DrinkCreateRequest();
        request.setName("Americano");
        request.setImage("americano.jpg");
        request.setBasePrice(BigDecimal.valueOf(120));
        request.setDrinkBaseId(1L);

        Drink mockDrink = new Drink();
        mockDrink.setId(3L);
        mockDrink.setName("Americano");
        mockDrink.setImage("americano.jpg");
        mockDrink.setBasePrice(BigDecimal.valueOf(120));
        mockDrink.setDefaultSyrups(new ArrayList<>());
        mockDrink.setAdditionalSyrups(new ArrayList<>());

        when(adminService.createDrink(any(DrinkCreateRequest.class))).thenReturn(mockDrink);

        mockMvc.perform(post("/admin/add/drink")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Americano"))
                .andExpect(jsonPath("$.basePrice").value(120));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void adminShouldUpdateDrink() throws Exception {
        DrinkCreateRequest updateRequest = new DrinkCreateRequest();
        updateRequest.setName("Old Drink");
        updateRequest.setImage("old-drink.jpg");
        updateRequest.setBasePrice(BigDecimal.valueOf(5.99));
        updateRequest.setDrinkBaseId(1L);
        updateRequest.setMilkId(1L);
        updateRequest.setDefaultSyrupIds(List.of(1L));

        Drink mockUpdatedDrink = new Drink();
        mockUpdatedDrink.setId(1L);
        mockUpdatedDrink.setName("Updated Drink");
        mockUpdatedDrink.setImage("updated-drink.jpg");
        mockUpdatedDrink.setBasePrice(BigDecimal.valueOf(5.99));
        mockUpdatedDrink.setDefaultSyrups(new ArrayList<>());
        mockUpdatedDrink.setAdditionalSyrups(new ArrayList<>());

        when(adminService.updateDrink(eq(1L), any(DrinkCreateRequest.class))).thenReturn(mockUpdatedDrink);

        mockMvc.perform(put("/admin/update/drink/1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Updated Drink"))
                .andExpect(jsonPath("$.image").value("updated-drink.jpg"))
                .andExpect(jsonPath("$.basePrice").value(5.99));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void adminShouldUpdateBakery() throws Exception {
        Bakery updatedBakery = new Bakery();
        updatedBakery.setName("Updated Bakery");
        updatedBakery.setImage("updated-bakery.jpg");
        updatedBakery.setPrice(BigDecimal.valueOf(3.99));
        updatedBakery.setDescription("Updated description");

        Bakery mockUpdatedBakery = new Bakery();
        mockUpdatedBakery.setId(1L);
        mockUpdatedBakery.setName("Updated Bakery");
        mockUpdatedBakery.setImage("updated-bakery.jpg");
        mockUpdatedBakery.setPrice(BigDecimal.valueOf(3.99));
        mockUpdatedBakery.setDescription("Updated description");

        when(adminService.updateBakery(eq(1L), any(Bakery.class))).thenReturn(mockUpdatedBakery);

        mockMvc.perform(put("/admin/update/bakery/1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(updatedBakery)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Updated Bakery"))
                .andExpect(jsonPath("$.price").value(3.99))
                .andExpect(jsonPath("$.description").value("Updated description"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void adminShouldUpdateDrinkBase() throws Exception {
        DrinkBase updatedDrinkBase = new DrinkBase();
        updatedDrinkBase.setName("Updated Base");
        updatedDrinkBase.setPrice(BigDecimal.valueOf(2.99));
        updatedDrinkBase.setDescription("Updated description");

        DrinkBase mockUpdatedDrinkBase = new DrinkBase();
        mockUpdatedDrinkBase.setId(1L);
        mockUpdatedDrinkBase.setName("Updated Base");
        mockUpdatedDrinkBase.setPrice(BigDecimal.valueOf(2.99));
        mockUpdatedDrinkBase.setDescription("Updated description");

        when(adminService.updateDrinkBase(eq(1L), any(DrinkBase.class))).thenReturn(mockUpdatedDrinkBase);

        mockMvc.perform(put("/admin/update/coffee/1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(updatedDrinkBase)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Updated Base"))
                .andExpect(jsonPath("$.price").value(2.99));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void adminShouldUpdateMilk() throws Exception {
        Milk updatedMilk = new Milk();
        updatedMilk.setName("Updated Milk");
        updatedMilk.setPrice(BigDecimal.valueOf(1.49));
        updatedMilk.setDescription("Updated description");

        Milk mockUpdatedMilk = new Milk();
        mockUpdatedMilk.setId(1L);
        mockUpdatedMilk.setName("Updated Milk");
        mockUpdatedMilk.setPrice(BigDecimal.valueOf(1.49));
        mockUpdatedMilk.setDescription("Updated description");

        when(adminService.updateMilk(eq(1L), any(Milk.class))).thenReturn(mockUpdatedMilk);

        mockMvc.perform(put("/admin/update/milk/1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(updatedMilk)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Updated Milk"))
                .andExpect(jsonPath("$.price").value(1.49));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void adminShouldUpdateSyrup() throws Exception {
        Syrup updatedSyrup = new Syrup();
        updatedSyrup.setName("Updated Syrup");
        updatedSyrup.setPrice(BigDecimal.valueOf(0.75));
        updatedSyrup.setDescription("Updated description");

        Syrup mockUpdatedSyrup = new Syrup();
        mockUpdatedSyrup.setId(1L);
        mockUpdatedSyrup.setName("Updated Syrup");
        mockUpdatedSyrup.setPrice(BigDecimal.valueOf(0.75));
        mockUpdatedSyrup.setDescription("Updated description");

        when(adminService.updateSyrup(eq(1L), any(Syrup.class))).thenReturn(mockUpdatedSyrup);

        mockMvc.perform(put("/admin/update/syrup/1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(updatedSyrup)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Updated Syrup"))
                .andExpect(jsonPath("$.price").value(0.75));
    }


    @Test
    @WithMockUser(roles = "ADMIN")
    void adminShouldDeleteDrink() throws Exception {
        doNothing().when(adminService).deleteDrink(1L);

        mockMvc.perform(delete("/admin/delete/drink/1"))
                .andExpect(status().isNoContent());

        verify(adminService, times(1)).deleteDrink(1L);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void adminShouldDeleteBakery() throws Exception {
        doNothing().when(adminService).deleteBakery(1L);

        mockMvc.perform(delete("/admin/delete/bakery/1"))
                .andExpect(status().isNoContent());

        verify(adminService, times(1)).deleteBakery(1L);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void adminShouldDeleteDrinkBase() throws Exception {
        doNothing().when(adminService).deleteDrinkBase(1L);

        mockMvc.perform(delete("/admin/delete/coffee/1"))
                .andExpect(status().isNoContent());

        verify(adminService, times(1)).deleteDrinkBase(1L);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void adminShouldDeleteMilk() throws Exception {
        doNothing().when(adminService).deleteMilk(1L);

        mockMvc.perform(delete("/admin/delete/milk/1"))
                .andExpect(status().isNoContent());

        verify(adminService, times(1)).deleteMilk(1L);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void adminShouldDeleteSyrup() throws Exception {
        doNothing().when(adminService).deleteSyrup(1L);

        mockMvc.perform(delete("/admin/delete/syrup/1"))
                .andExpect(status().isNoContent());

        verify(adminService, times(1)).deleteSyrup(1L);
    }

    @Test
    @WithMockUser(roles = "USER")
    void userShouldNotCreateDrink() throws Exception {
        DrinkCreateRequest request = new DrinkCreateRequest();
        request.setName("Forbidden Drink");
        request.setBasePrice(BigDecimal.valueOf(100));
        request.setDrinkBaseId(1L);

        mockMvc.perform(post("/admin/add/drink")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "USER")
    void userShouldNotUpdateDrink() throws Exception {
        mockMvc.perform(put("/admin/update/drink/1")
                        .contentType("application/json")
                        .content("{}"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "USER")
    void userShouldNotDeleteDrink() throws Exception {
        mockMvc.perform(delete("/admin/delete/drink/1"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "USER")
    void userShouldNotCreateBakery() throws Exception {
        mockMvc.perform(post("/admin/add/bakery")
                        .contentType("application/json")
                        .content("{}"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "USER")
    void userShouldNotDeleteBakery() throws Exception {
        mockMvc.perform(delete("/admin/delete/bakery/1"))
                .andExpect(status().isForbidden());
    }

    @Test
    void unauthenticatedShouldNotCreateDrink() throws Exception {
        DrinkCreateRequest request = new DrinkCreateRequest();
        request.setName("Unauthorized Drink");
        request.setBasePrice(BigDecimal.valueOf(100));
        request.setDrinkBaseId(1L);

        mockMvc.perform(post("/admin/add/drink")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void unauthenticatedShouldNotUpdateDrink() throws Exception {
        mockMvc.perform(put("/admin/update/drink/1")
                        .contentType("application/json")
                        .content("{}"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void unauthenticatedShouldNotDeleteDrink() throws Exception {
        mockMvc.perform(delete("/admin/delete/drink/1"))
                .andExpect(status().isUnauthorized());
    }


    @Test
    @WithMockUser(roles = "ADMIN")
    void adminShouldGetNotFoundWhenDrinkNotExists() throws Exception {
        when(adminService.updateDrink(eq(999L), any(DrinkCreateRequest.class)))
                .thenThrow(new jakarta.persistence.EntityNotFoundException("Drink not found"));

        DrinkCreateRequest request = new DrinkCreateRequest();
        request.setName("Ghost Drink");
        request.setBasePrice(BigDecimal.valueOf(100));
        request.setDrinkBaseId(1L);

        mockMvc.perform(put("/admin/update/drink/999")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void adminShouldGetNotFoundWhenBakeryNotExists() throws Exception {
        when(adminService.updateBakery(eq(999L), any(Bakery.class)))
                .thenThrow(new jakarta.persistence.EntityNotFoundException("Bakery not found"));

        Bakery bakery = new Bakery();
        bakery.setName("Ghost Bakery");
        bakery.setPrice(BigDecimal.valueOf(2.99));

        mockMvc.perform(put("/admin/update/bakery/999")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(bakery)))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void adminShouldGetNotFoundWhenDeletingNonExistentDrink() throws Exception {
        doThrow(new jakarta.persistence.EntityNotFoundException("Drink not found"))
                .when(adminService).deleteDrink(999L);

        mockMvc.perform(delete("/admin/delete/drink/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void adminShouldGetNotFoundWhenMilkNotExists() throws Exception {
        when(adminService.updateMilk(eq(999L), any(Milk.class)))
                .thenThrow(new jakarta.persistence.EntityNotFoundException("Milk not found"));

        Milk milk = new Milk();
        milk.setName("Ghost Milk");
        milk.setPrice(BigDecimal.valueOf(0.99));

        mockMvc.perform(put("/admin/update/milk/999")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(milk)))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void adminShouldGetNotFoundWhenSyrupNotExists() throws Exception {
        when(adminService.updateSyrup(eq(999L), any(Syrup.class)))
                .thenThrow(new jakarta.persistence.EntityNotFoundException("Syrup not found"));

        Syrup syrup = new Syrup();
        syrup.setName("Ghost Syrup");
        syrup.setPrice(BigDecimal.valueOf(0.50));

        mockMvc.perform(put("/admin/update/syrup/999")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(syrup)))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void adminShouldGetBadRequestWhenCreatingDrinkWithEmptyBody() throws Exception {
        mockMvc.perform(post("/admin/add/drink")
                        .contentType("application/json")
                        .content(""))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void adminShouldGetBadRequestWhenCreatingBakeryWithEmptyBody() throws Exception {
        mockMvc.perform(post("/admin/add/bakery")
                        .contentType("application/json")
                        .content(""))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void adminShouldGetBadRequestWhenCreatingMilkWithEmptyBody() throws Exception {
        mockMvc.perform(post("/admin/add/milk")
                        .contentType("application/json")
                        .content(""))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void adminShouldGetBadRequestWhenCreatingSyrupWithEmptyBody() throws Exception {
        mockMvc.perform(post("/admin/add/syrup")
                        .contentType("application/json")
                        .content(""))
                .andExpect(status().isBadRequest());
    }
}
