package com.example.demo.controllers;

import com.example.demo.models.Day;
import com.example.demo.models.Record;
import com.example.demo.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static com.example.demo.DemoApplication.DATE_FORMATTER;
import static com.example.demo.DemoApplication.TIME_FORMATTER;

/**
 * Класс обрабатывающий все запросы по пути "/booking".
 * Содержит свойство <b>bookingService</b>.
 */
@RestController
@RequestMapping(value = "/booking")
@CrossOrigin
public class BookingController {

    /** Объект для работы с бронированием*/
    BookingService bookingService;

    /** Конструктор - создание нового объекта */
    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    /**
     * Метод для получения броней на определённую дату.
     * Принимает параметр с названием "date". Это строка, содержащую дату. Формат строки описывает DATE_FORMATTER.
     * @see com.example.demo.DemoApplication#DATE_FORMATTER
     * @see BookingService#getAllDesksCalendars(LocalDate)
     */
    @GetMapping("/getRecord")
    @ResponseBody
    public List<Day> getRecord( @RequestParam(value = "date") String dateStr ){
        return bookingService.getAllDesksCalendars(LocalDate.parse(dateStr, DATE_FORMATTER));
    }

    /**
     * Метод для создания и сохранения брони.
     * timeStart - время начала брони, timeEnd - время конца брони.
     * @see com.example.demo.DemoApplication#DATE_FORMATTER
     * @see com.example.demo.DemoApplication#TIME_FORMATTER
     * @see BookingService#saveRecord(Record)
     */
    @GetMapping("/makeRecord")
    @ResponseBody
    public void makeRecord(@RequestParam(value = "deskNumber") int deskNumber,
                           @RequestParam(value = "date") String dateStr,
                           @RequestParam(value = "timeStart") String startStr,
                           @RequestParam(value = "timeEnd") String endStr,
                           @RequestParam(value = "clientName") String clientName,
                           @RequestParam(value = "clientSecondName") String clientSecondName,
                           @RequestParam(value = "clientTelNumber") String clientTelNumber
                           ){
        LocalDate date = LocalDate.parse(dateStr, DATE_FORMATTER);
        LocalTime start = LocalTime.parse(startStr, TIME_FORMATTER);
        LocalTime end = LocalTime.parse(endStr, TIME_FORMATTER);

        Record record = new Record();
        record.setDeskNumber(deskNumber);
        record.setDate(date);
        record.setStart(start);
        record.setEnd(end);
        record.setClientName(clientName);
        record.setClientSecondName(clientSecondName);
        record.setClientTelNumber(clientTelNumber);
        bookingService.saveRecord(record);
    }
}
