package com.example.demo.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/booking")
@CrossOrigin
public class BookingController {

    
    @GetMapping("/{date}")
    @ResponseBody
    public List<String> checkDate(@PathVariable("date") String date){
        return null;
    }
}
