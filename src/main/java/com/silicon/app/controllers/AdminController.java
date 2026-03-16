package com.silicon.app.controllers;

import com.silicon.app.models.entities.*;
import com.silicon.app.models.requests.DrinkCreateRequest;
import com.silicon.app.services.AdminService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // ===========================================GET==========================================
    @GetMapping("/add/drink")
    public List<Drink> getDrink() {
        return adminService.getAllDrinks();
    }

    @GetMapping("add/coffee")
    public List<DrinkBase> getCoffee() {
        return adminService.getAllCoffee();
    }

    @GetMapping("add/milk")
    public List<Milk> getMilk() {
        return adminService.getAllMilks();
    }

    @GetMapping("add/syrup")
    public List<Syrup> getSyrup() {
        return adminService.getAllSyrups();
    }

    @GetMapping("add/bakery")
    public List<Bakery> getBakery() {
        return adminService.getAllBakery();
    }

    // ===========================================POST========================================
    @PostMapping("/add/drink")
    public ResponseEntity<Drink> addDrink(@Valid @RequestBody DrinkCreateRequest request) {
        log.info("Received request to add drink: {}", request.getName());
        Drink drink = adminService.createDrink(request);
        log.info("Drink created with ID: {}", drink.getName());
        return ResponseEntity.ok(drink);
    }

    @PostMapping("/add/bakery")
    public ResponseEntity<Bakery> addBakery(@Valid @RequestBody Bakery bakery) {
        log.info("Received request to add bakery: {}", bakery.getName());
        Bakery createdBakery = adminService.createBakery(bakery);
        log.info("Bakery created with ID: {}", createdBakery.getId());
        return ResponseEntity.ok(createdBakery);
    }

    @PostMapping("/add/coffee")
    public ResponseEntity<DrinkBase> addCoffee(@Valid @RequestBody DrinkBase drinkBase) {
        log.info("Received request to add coffee: {}", drinkBase.getName());
        DrinkBase createdDrinkBase = adminService.createDrinkBase(drinkBase);
        log.info("Coffee created with ID: {}", createdDrinkBase.getId());
        return ResponseEntity.ok(createdDrinkBase);
    }

    @PostMapping("/add/milk")
    public ResponseEntity<Milk> addMilk(@Valid @RequestBody Milk milk) {
        log.info("Received request to add milk: {}", milk.getName());
        Milk createdMilk = adminService.createMilk(milk);
        log.info("Milk created with ID: {}", createdMilk.getId());
        return ResponseEntity.ok(createdMilk);
    }

    @PostMapping("/add/syrup")
    public ResponseEntity<Syrup> addSyrup(@Valid @RequestBody Syrup syrup) {
        log.info("Received request to add syrup: {}", syrup.getName());
        Syrup createdSyrup = adminService.createSyrup(syrup);
        log.info("Syrup created with ID: {}", createdSyrup.getId());
        return ResponseEntity.ok(createdSyrup);
    }

    //============================================PUT==========================================
    @PutMapping("/update/drink/{id}")
    public ResponseEntity<Drink> updateDrink(@PathVariable Long id, @RequestBody DrinkCreateRequest request) {
        Drink updated = adminService.updateDrink(id, request);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/update/bakery/{id}")
    public ResponseEntity<Bakery> updateBakery(@PathVariable Long id, @RequestBody Bakery bakery) {
        Bakery updated = adminService.updateBakery(id, bakery);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/update/coffee/{id}")
    public ResponseEntity<DrinkBase> updateCoffee(@PathVariable Long id, @RequestBody DrinkBase drinkBase) {
        DrinkBase updated = adminService.updateDrinkBase(id, drinkBase);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/update/milk/{id}")
    public ResponseEntity<Milk> updateMilk(@PathVariable Long id, @RequestBody Milk milk) {
        Milk updated = adminService.updateMilk(id, milk);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/update/syrup/{id}")
    public ResponseEntity<Syrup> updateSyrup(@PathVariable Long id, @RequestBody Syrup syrup) {
        Syrup updated = adminService.updateSyrup(id, syrup);
        return ResponseEntity.ok(updated);
    }

    // ==========================================DELETE===========================================
    @DeleteMapping("/delete/drink/{id}")
    public ResponseEntity<Void> deleteDrink(@PathVariable Long id) {
        adminService.deleteDrink(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/bakery/{id}")
    public ResponseEntity<Void> deleteBakery(@PathVariable Long id) {
        adminService.deleteBakery(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/coffee/{id}")
    public ResponseEntity<Void> deleteCoffee(@PathVariable Long id) {
        adminService.deleteDrinkBase(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/milk/{id}")
    public ResponseEntity<Void> deleteMilk(@PathVariable Long id) {
        adminService.deleteMilk(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/syrup/{id}")
    public ResponseEntity<Void> deleteSyrup(@PathVariable Long id) {
        adminService.deleteSyrup(id);
        return ResponseEntity.noContent().build();
    }
}
