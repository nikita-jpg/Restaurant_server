package com.example.demo.controllers;


import com.example.demo.services.DishService;
import com.example.demo.models.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.example.demo.DemoApplication.PATH_IMG;

@Controller
public class ControllerAdmin {
    private DishService dishService;


    @Autowired
    public ControllerAdmin(DishService dishService){
        this.dishService = dishService;
    }


    @GetMapping(value = "/admin")
    public String start(Model model){
        model.addAttribute("newDish", new Dish());
        return "admin/index";
    }

    //Загружаем новое блюдо на севрер
    @PostMapping(value = "/admin")
    public String uploadDish(Dish dish, @RequestParam("file") MultipartFile file) {
        dishService.save(dish);
        String name = Integer.toString(dish.getId());
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(PATH_IMG + name + ".jpg"));
                stream.write(bytes);
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }}
        return "redirect:/admin";
    }
}
