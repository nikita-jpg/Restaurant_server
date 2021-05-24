package com.example.demo.controllers;
import com.example.demo.models.Dish;
import com.example.demo.services.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static com.example.demo.DemoApplication.PATH_IMG;

@RestController()
@RequestMapping(value = "/menu")
@CrossOrigin
public class DishController {
    private DishService dishService;

    @Autowired
    DishController(DishService dishService){
        this.dishService = dishService;

    }


    @GetMapping("")
    public List<Dish> getMenu(){
        return dishService.getAll();
    }


    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<byte[]> getPhoto(@PathVariable("id") String id) throws IOException {
        File imgPath = new File(PATH_IMG + id + ".jpg" );
        byte[] image = Files.readAllBytes(imgPath.toPath());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(image.length);
        return new ResponseEntity<>(image, headers, HttpStatus.OK);
    }
}
