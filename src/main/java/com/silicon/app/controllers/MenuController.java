package com.silicon.app.controllers;

import com.silicon.app.models.entities.*;
import com.silicon.app.repositories.BakeryRepository;
import com.silicon.app.repositories.DrinkRepository;
import com.silicon.app.repositories.MilkRepository;
import com.silicon.app.repositories.SyrupRepository;
import com.silicon.app.services.MenuService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
@CrossOrigin(origins = "*")
public class MenuController {
    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/drinks")
    public List<Drink> getDrinkList() {
        return menuService.getAllDrinks();
    }

    @GetMapping("/bakery")
    public List<Bakery> getBakeryList() {
        return menuService.getAllBakery();
    }

    @GetMapping("/syrups")
    public List<Syrup> getSyrups() {
        return menuService.getAllSyrups();
    }

    @GetMapping("/milks")
    public List<Milk> getMilks() {
        return menuService.getAllMilks();
    }

//    @GetMapping("/bakery/filter")
//    public List<Bakery> getBakeryWithoutAllergens(@RequestParam List<AllergenType> allergens) {
//        return menuService.getAllWithoutAllergens(allergens);
//    }
}
