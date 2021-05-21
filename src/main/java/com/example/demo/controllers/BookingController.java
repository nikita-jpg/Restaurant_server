package com.example.demo.controllers;

import com.example.demo.models.Desk;
import com.example.demo.models.Record;
import com.example.demo.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

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
    public Desk getRecord(){
        return bookingService.getDeskById(1);
//        Calendar calendar = new GregorianCalendar(2017, 0 , 25);
//        Record record = new Record();
//        record.setCalendar(calendar);
//        record.setDesk(bookingService.getDeskById(1));
//        bookingService.saveRecord(record);
    }

    @GetMapping("/makeRecord")
    @ResponseBody
    public void makeRecord(){
        Calendar calendar = new GregorianCalendar(2017, 0 , 25);
        Record record = new Record();
        record.setCalendar(calendar);
        record.setDesk_number(bookingService.getDeskById(1).getDeskNumber());
        bookingService.getDeskById(1).getRecords().add(record);
        bookingService.saveRecord(record);
    }
}
