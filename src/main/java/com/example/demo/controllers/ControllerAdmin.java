package com.example.demo.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
//@CrossOrigin
//@RequestMapping(value = "/admin")
public class ControllerAdmin {

    @GetMapping(value = "/admin")
    public String checkAdmin(){
//        model.addAllAttributes()
        return "admin/index";
    }
}
