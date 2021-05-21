package com.example.demo.repository;

import com.example.demo.models.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository<Dish, Integer> {

//    @Override
//    <S extends Dish> S save(S s);
}