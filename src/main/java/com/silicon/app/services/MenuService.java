package com.silicon.app.services;

import com.silicon.app.models.entities.*;
import com.silicon.app.repositories.BakeryRepository;
import com.silicon.app.repositories.DrinkRepository;
import com.silicon.app.repositories.MilkRepository;
import com.silicon.app.repositories.SyrupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {
    private final DrinkRepository drinkRepository;
    private final BakeryRepository bakeryRepository;
    private final SyrupRepository syrupRepository;
    private final MilkRepository milkRepository;

    public MenuService(DrinkRepository drinkRepository, BakeryRepository bakeryRepository, SyrupRepository syrupRepository, MilkRepository milkRepository) {
        this.drinkRepository = drinkRepository;
        this.bakeryRepository = bakeryRepository;
        this.syrupRepository = syrupRepository;
        this.milkRepository = milkRepository;
    }

//    public List<Bakery> getAllWithoutAllergens(List<AllergenType> allergens){
//        return bakeryRepository.findAllWithoutAllergens(allergens);
//    }

    public List<Drink> getAllDrinks() {
        return drinkRepository.findAll();
    }

    public List<Bakery> getAllBakery() {
        return bakeryRepository.findAll();
    }

    public List<Syrup> getAllSyrups() {
        return syrupRepository.findAll();
    }

    public  List<Milk> getAllMilks() {
        return milkRepository.findAll();
    }
}
