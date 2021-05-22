package com.example.demo.controllers;

import com.example.demo.models.Desk;
import com.example.demo.models.Record;
import com.example.demo.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.example.demo.DemoApplication.DATE_FORMAT;

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
    public Map<Integer, List<Calendar>> getRecord(){
        return bookingService.getAllDesksCalendars();
//        Calendar calendar = new GregorianCalendar(2017, 0 , 25);
//        Record record = new Record();
//        record.setCalendar(calendar);
//        record.setDesk(bookingService.getDeskById(1));
//        bookingService.saveRecord(record);
    }

    @GetMapping("/makeRecord")
    @ResponseBody
    public void makeRecord(@RequestParam(value = "deskNumber") int deskNumber,
                           @RequestParam(value = "date") String dateStr,
                           @RequestParam(value = "start") String startStr,
                           @RequestParam(value = "end") String endStr
                           ){
        LocalDate date = LocalDate.parse(dateStr, DATE_FORMAT);
        Record record = new Record();
        record.setDeskNumber(deskNumber);
        record.setDate(date);
//        record.setStart(start);
//        record.setEnd(end);
        bookingService.saveRecord(record);
    }
}
