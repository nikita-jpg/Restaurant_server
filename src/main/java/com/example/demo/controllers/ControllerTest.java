package com.example.demo.controllers;
import com.example.demo.User;
import com.example.demo.models.Menu;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@CrossOrigin
public class ControllerTest {
    private String BASE_URL = "http://localhost:8080/menu/";

    @GetMapping("/menu")
    public Menu getMenu(){
        Menu menu = new Menu();
        menu.setTitle("Загаловок");
        menu.setDescription("Описание");
        menu.setImg(BASE_URL + "1");
        return menu;
    }


    @GetMapping("/menu/{id}")
    @ResponseBody
    public ResponseEntity<byte[]> getPhoto(@PathVariable("id") String id) throws IOException {

        File imgPath = new File("C:\\Users\\Nikita\\Desktop\\images\\test_" + id + ".jpg" );

        byte[] image = Files.readAllBytes(imgPath.toPath());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(image.length);
        return new ResponseEntity<>(image, headers, HttpStatus.OK);
    }
}
