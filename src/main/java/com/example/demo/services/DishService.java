package com.example.demo.services;

import com.example.demo.models.Dish;
import com.example.demo.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Класс служит для работы с сущностями в БД"
 * Содержит свойства <b>dishRepository</b>.
 */
@Service
public class DishService {

    /** Хранит Репозиторий для работы с блюдами*/
    private DishRepository dishRepository;

    /** Конструктор - создание нового объекта */
    @Autowired
    public DishService(DishRepository dishRepository){
        this.dishRepository = dishRepository;
    }

    /** Метод для сохраенения блюда в БД */
    public void save(Dish dish){
        dishRepository.save(dish);
    }

    /** Метод для получения всех блюда из БД */
    public List<Dish> getAll(){
        return dishRepository.findAll();
    }
}
