package com.example.demo.services;

import com.example.demo.models.Desk;
import com.example.demo.models.Record;
import com.example.demo.repository.DeskRepository;
import com.example.demo.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public Map<Integer,List<Calendar>> getAllDesksCalendars(){
        List<Desk> desks = deskRepository.findAll();
        List<Record> records;
        Map<Integer,List<Calendar>> dictionary = new HashMap<>();
        List<Calendar> calendars = new ArrayList<>();

//        for(int i=1;i<desks.size()+1;i++){
//            records = desks.get(i-1).getRecords();
//            if(records.size()!=0){
//                for(int j=0;j<records.size();j++)
//                    calendars.add(records.get(j).getCalendar());
//                dictionary.put(i,calendars);
//                calendars = new ArrayList<>();
//            }
//        }
        return dictionary;
    }
}
