package com.example.demo.controllers;


import com.example.demo.models.Record;
import com.example.demo.repository.DeskRepository;
import com.example.demo.repository.RecordRepository;
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

/**
 * Класс обрабатывающий все запросы по пути "/admin".
 * Содержит свойства <b>dishService</b> и <b>bookingService</b>.
 */
@RequestMapping(value = "/admin")
@Controller
public class AdminController {

    /** Объект класса для работы с блюдами*/
    private DishService dishService;

    /** Объект для работы с бронированием*/
    private BookingService bookingService;


    /** Конструктор - создание нового объекта */
    @Autowired
    public AdminController(DishService dishService, BookingService bookingService){
        this.dishService = dishService;
        this.bookingService = bookingService;
    }

    /** Перенаправляем с запроса ""->/admin/dishPage*/
    @GetMapping(value = "")
    public String getFirstPage(){
        return "redirect:/admin/dishPage";
    }

    /** Добавляем в model объект Dish. Это нужно для получения блюд с сервера*/
    @GetMapping(value = "/dishPage")
    public String getDishPage(Model model){
        model.addAttribute("newDish", new Dish());
        return "/admin/dishPage";
    }

    /**
     * Метод для загрузки нового блюда на сервер.
     * Загружаем новое блюдо на сервер по /uploadDish.
     * Метод получает объект класса Dish и картинку к нему.
     * Сохраняем блюдо в БД.
     * Если картинка существует, о сохраняем её по адресу PATH_IMG + name + ".jpg".
     * PATH_IMG - глобальная константа.
     * name = id полученного блюда, то есть name = Integer.toString(dish.getId()).
     * @see com.example.demo.DemoApplication#PATH_IMG
     */
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
        return "redirect:/admin/dishPage";
    }

    /**
     * Перенаправляет /searchPage -> /admin/searchPage
     */
    @GetMapping(value = "/searchPage")
    public String getSearchPage(){
        return "/admin/searchPage";
    }

    /**
     * Метод для получения броней клиента.
     * Принимает имя и фамилию клиента. С помощью bookingService находит все брони клиента.
     * Добавляет в model атрибут "recordList" со списком броней клиента.
     * Перенаправляет /searchClient -> /admin/searchPage.
     * @see BookingService#getRecordsByNameAndSecondName(String, String) 
     */
    @PostMapping(value = "/searchClient")
    public String searchClient(@RequestParam("clientName") String clientName, @RequestParam("clientSecondName") String clientSecondName, Model model){
        List<Record> list = bookingService.getRecordsByNameAndSecondName(clientName, clientSecondName);
        model.addAttribute("recordList",list);
        return "/admin/searchPage";
    }

    /**
     * Метод для удаления клиента.
     * Принимает id клиента.
     * С помощью bookingService удалет клиента.
     *  @see BookingService#deleteRecordById(int) 
     */
    @PostMapping(value = "/searchClient/delete")
    public String deleteRecord(@RequestParam("id") int id){
        bookingService.deleteRecordById(id);
        return "/admin/searchPage";
    }


}
