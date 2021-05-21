package com.example.demo.services;

import com.example.demo.models.Dish;
import com.example.demo.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {
    private DishRepository dishRepository;

    @Autowired
    public DishService(DishRepository dishRepository){
        this.dishRepository = dishRepository;
    }

    public void save(Dish dish){
        dishRepository.save(dish);
    }

    public List<Dish> getAll(){
        return dishRepository.findAll();
    }
}
