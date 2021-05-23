package com.example.demo.repository;

import com.example.demo.models.Desk;
import com.example.demo.models.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeskRepository extends JpaRepository<Desk, Integer> {

//    @Query("SELECT * FROM DESKS, RECORDS WHERE DESKS.ID=DESK_NUMBER AND RECORDS.")
//    public List<Record> findAllBy
}
