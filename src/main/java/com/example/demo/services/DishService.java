package com.example.demo.services;

import com.example.demo.models.Dish;
import com.example.demo.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {
    private MenuRepository menuRepository;

    @Autowired
    public DishService(MenuRepository menuRepository){
        this.menuRepository = menuRepository;
    }

    public void save(Dish dish){
        menuRepository.save(dish);
    }

    public List<Dish> getAll(){
        return menuRepository.findAll();
    }
}
