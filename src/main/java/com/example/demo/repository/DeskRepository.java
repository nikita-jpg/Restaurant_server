package com.example.demo.repository;

import com.example.demo.models.Desk;
import com.example.demo.models.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeskRepository extends JpaRepository<Desk, Integer> {

}
