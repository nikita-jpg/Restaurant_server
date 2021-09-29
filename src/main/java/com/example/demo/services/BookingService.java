package com.example.demo.services;

import com.example.demo.models.Day;
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

/**
 * Класс служит для работы с сущностями в БД
 * Содержит свойства <b>recordRepository</b> и <b>deskRepository</b>.
 */
@Service
public class BookingService {

    /** Хранит Репозиторий для работы с бронями*/
    private RecordRepository recordRepository;

    /** Хранит Репозиторий для работы со столиками*/
    private DeskRepository deskRepository;

    /** Конструктор - создание нового объекта */
    @Autowired
    public BookingService(RecordRepository recordRepository, DeskRepository deskRepository){
        this.recordRepository = recordRepository;
        this.deskRepository = deskRepository;
    }

    /**Метод служит для сохранения брони в БД*/
    public void saveRecord(Record record){
        recordRepository.save(record);
    }

    /**Метод служит для удаления брони из БД*/
    public void deleteRecordById(int id){
        recordRepository.deleteById(id);
    }

    /**Метод служит для получения списка броней пользователя по имени и фамилии из БД*/
    public List<Record> getRecordsByNameAndSecondName(String clientName,String clientSecondName){
        return recordRepository.findAllByClientNameAndClientSecondName(clientName, clientSecondName);
    }

    /**Метод служит для получения столика по его id из БД. <b>В проекте нигде не используется</b>"*/
    public Desk getDeskById(int id){
        return deskRepository.findById(id).get();
    }

    /**Метод служит для получения всех столиков из БД по дате.
     * @see com.example.demo.DemoApplication#DATE_FORMATTER
     */
    public List<Day> getAllDesksCalendars(LocalDate neededDate){
        List<Day> list = new ArrayList<>();

        //Получили все активные столы.
        List<Desk> desks = deskRepository.findAll();

        //Пробегаемся по всем активным столам
        for(int i=0;i<desks.size();i++){
            Day day = new Day();
            day.setTableId(desks.get(i).getDeskNumber());

            //Получаем записи к активным столам с пометкой на дату
            List<Record> records = recordRepository.findRecordByDeskNumberAndDate(i,neededDate);

            //Получаем список свободных временных промежутков
            day.setFreeTime(getFreeTimeList(records));
            list.add(day);

        }

        return list;
    }

    /**Метод служит для получения списка свободного времени для бронирования на неделю для каждого столика
     * @see com.example.demo.DemoApplication#OPEN_TIME_WORKING_WEEK
     * @see com.example.demo.DemoApplication#CLOSE_TIME_WORKING_WEEK
     * @return "номер столика":["начала свободного промежутка","конец свободного промежутка"] Пример: {"1":["9:00","18:30","21:30","23:30"], "2":["10:00","14:30","21:30","23:30"]}
     */
    public List<String> getFreeTimeList(List<Record> records){
        List<String> timeList = new ArrayList<>();
        if(records.size()==0){
            timeList.add(OPEN_TIME_WORKING_WEEK.toString() + " - " + CLOSE_TIME_WORKING_WEEK.toString());
            return timeList;
        }

        LocalTime start = OPEN_TIME_WORKING_WEEK;
        LocalTime finish = records.get(0).getStart();

        if( (finish.getHour()*60+finish.getMinute()) - (start.getHour()*60+start.getMinute()) > 90){
            timeList.add(start.toString() + " - " + finish.minusMinutes(30).toString());
        }

        for (int i=1;i<records.size()-1;i++){
            start = records.get(i).getEnd();
            finish = records.get(i+1).getStart();
            if( (finish.getHour()*60+finish.getMinute()) - (start.getHour()*60+start.getMinute()) > 90){
                timeList.add(start.plusMinutes(30).toString() + " - " + finish.minusMinutes(30).toString());
            }
        }

        start = records.get(records.size()-1).getEnd();
        finish = CLOSE_TIME_WORKING_WEEK;
        if( (finish.getHour()*60+finish.getMinute()) - (start.getHour()*60+start.getMinute()) > 90){
            timeList.add(start.plusMinutes(30).toString() + " - " + finish.toString());
        }
        return timeList;
    }
}
