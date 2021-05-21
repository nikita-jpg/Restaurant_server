package com.example.demo.services;

import com.example.demo.models.Desk;
import com.example.demo.models.Record;
import com.example.demo.repository.DeskRepository;
import com.example.demo.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    private RecordRepository recordRepository;
    private DeskRepository deskRepository;

    @Autowired
    public BookingService(RecordRepository recordRepository, DeskRepository deskRepository){
        this.recordRepository = recordRepository;
        this.deskRepository = deskRepository;
    }

//    public

    public void saveRecord(Record record){
        recordRepository.save(record);
    }

    public Desk getDeskById(int id){
        return deskRepository.findById(id).get();
    }
}
