package com.example.demo.repository;

import com.example.demo.models.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record, Integer> {

    public List<Record> findRecordByDeskNumberAndDate(int deskNumber, LocalDate date);
}
