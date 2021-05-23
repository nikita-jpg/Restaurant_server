package com.example.demo.controllers;

import com.example.demo.models.Record;
import com.example.demo.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static com.example.demo.DemoApplication.DATE_FORMATTER;
import static com.example.demo.DemoApplication.TIME_FORMATTER;

@RestController
@RequestMapping(value = "/booking")
@CrossOrigin
public class BookingController {
    BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/getRecord")
    @ResponseBody
    public Map<Integer, List<LocalTime>> getRecord(
            @RequestParam(value = "date") String dateStr){
        return bookingService.getAllDesksCalendars(LocalDate.parse(dateStr, DATE_FORMATTER));
    }

    @GetMapping("/makeRecord")
    @ResponseBody
    public void makeRecord(@RequestParam(value = "deskNumber") int deskNumber,
                           @RequestParam(value = "date") String dateStr,
                           @RequestParam(value = "start") String startStr,
                           @RequestParam(value = "end") String endStr,
                           @RequestParam(value = "clientName") String clientName
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
        bookingService.saveRecord(record);
    }
}
