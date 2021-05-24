package com.example.demo.controllers;


import com.example.demo.models.Record;
import com.example.demo.services.BookingService;
import com.example.demo.services.DishService;
import com.example.demo.models.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import static com.example.demo.DemoApplication.PATH_IMG;

@RequestMapping(value = "/admin")
@Controller
public class AdminController {
    private DishService dishService;
    private BookingService bookingService;


    @Autowired
    public AdminController(DishService dishService, BookingService bookingService){
        this.dishService = dishService;
        this.bookingService = bookingService;
    }

    @GetMapping(value = "")
    public String getFirstPage(){
        return "redirect:/admin/dishPage";
    }

    @GetMapping(value = "/dishPage")
    public String getDishPage(Model model){
        model.addAttribute("newDish", new Dish());
        return "/admin/dishPage";
    }

    //Загружаем новое блюдо на севрер
    @PostMapping(value = "/uploadDish")
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
        return "/admin/dishPage";
    }

    @GetMapping(value = "/searchPage")
    public String getSearchPage(){
        return "/admin/searchPage";
    }

    @PostMapping(value = "/searchClient")
    public String searchClient(@RequestParam("clientName") String clientName, @RequestParam("clientSecondName") String clientSecondName, Model model){
        List<Record> list = bookingService.getRecordsByNameAndSecondName(clientName, clientSecondName);
        model.addAttribute("recordList",list);
        return "/admin/searchPage";
    }

    @PostMapping(value = "/searchClient/delete")
    public String deleteRecord(@RequestParam("id") int id){
        bookingService.deleteRecordById(id);
        return "/admin/searchPage";
    }


//    @PostMapping(value = "/admin/searchClient")
//    public String getRecords(@RequestParam("clientName") String clientName, Model model){
//        List<Record> list = bookingService.getRecordsByName(clientName);
//        model.addAttribute("recordList",list);
//        return "redirect:/admin";
//    }
}
