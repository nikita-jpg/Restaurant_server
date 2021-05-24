package com.example.demo.services;

import com.example.demo.models.Desk;
import com.example.demo.models.Record;
import com.example.demo.repository.DeskRepository;
import com.example.demo.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static com.example.demo.DemoApplication.CLOSE_TIME_WORKING_WEEK;
import static com.example.demo.DemoApplication.OPEN_TIME_WORKING_WEEK;

@Service
public class BookingService {
    private RecordRepository recordRepository;
    private DeskRepository deskRepository;

    @Autowired
    public BookingService(RecordRepository recordRepository, DeskRepository deskRepository){
        this.recordRepository = recordRepository;
        this.deskRepository = deskRepository;
    }

    public void saveRecord(Record record){
        recordRepository.save(record);
    }
    public void deleteRecordById(int id){
        recordRepository.deleteById(id);
    }
    public List<Record> getRecordsByNameAndSecondName(String clientName,String clientSecondName){
        return recordRepository.findAllByClientNameAndClientSecondName(clientName, clientSecondName);
    }


    public Desk getDeskById(int id){
        return deskRepository.findById(id).get();
    }
    public Map<Integer,List<String>> getAllDesksCalendars(LocalDate neededDate){
        Map<Integer,List<String>> map = new LinkedHashMap<>();

        //Тут хранится список свободных временных промежутков
        List<String> timeList;

        //Получили все активные столы.
        List<Desk> desks = deskRepository.findAll();

        //Пробегаемся по всем активным столам
        for(int i=1;i<desks.size()+1;i++){

            //Получаем записи к активным столам с пометкой на дату
            List<Record> records = recordRepository.findRecordByDeskNumberAndDate(i,neededDate);

            //Получаем список свободных временных промежутков
            timeList = getFreeTimeList(records);

            map.put(i,timeList);
        }

        return map;
    }


    public List<String> getFreeTimeList(List<Record> records){
        List<String> timeList = new ArrayList<>();
        if(records.size()==0){
            timeList.add(OPEN_TIME_WORKING_WEEK.toString());
            timeList.add(CLOSE_TIME_WORKING_WEEK.toString());
            return timeList;
        }

        LocalTime start = OPEN_TIME_WORKING_WEEK;
        LocalTime finish = records.get(0).getStart();

        if( (finish.getHour()*60+finish.getMinute()) - (start.getHour()*60+start.getMinute()) > 90){
            timeList.add(start.toString());
            timeList.add(finish.minusMinutes(30).toString());
        }

        for (int i=1;i<records.size()-1;i++){
            start = records.get(i).getEnd();
            finish = records.get(i+1).getStart();
            if( (finish.getHour()*60+finish.getMinute()) - (start.getHour()*60+start.getMinute()) > 90){
                timeList.add(start.plusMinutes(30).toString());
                timeList.add(finish.minusMinutes(30).toString());
            }
        }

        start = records.get(records.size()-1).getEnd();
        finish = CLOSE_TIME_WORKING_WEEK;
        if( (finish.getHour()*60+finish.getMinute()) - (start.getHour()*60+start.getMinute()) > 90){
            timeList.add(start.plusMinutes(30).toString());
            timeList.add(finish.toString());
        }
        return timeList;
    }
}
