package com.example.demo.controllers;


import com.example.demo.services.DishService;
import com.example.demo.models.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
//@CrossOrigin
//@RequestMapping(value = "/admin")
public class ControllerAdmin {
    private DishService dishService;

    @Autowired
    public ControllerAdmin(DishService dishService){
        this.dishService = dishService;
    }

    @GetMapping(value = "/admin")
    public String checkAdmin(Model model){
        model.addAttribute("newDish", new Dish());
        return "admin/index";
    }

    @PostMapping(value = "/admin")
    public String checkAdmin(Dish dish){
        dishService.save(dish);
        return "redirect:/admin";
    }
}
