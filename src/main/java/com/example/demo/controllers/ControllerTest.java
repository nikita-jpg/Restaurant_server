package com.example.demo.controllers;
import com.example.demo.models.Dish;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static com.example.demo.DemoApplication.PATH_IMG;

@RestController()
@RequestMapping(value = "/menu")
@CrossOrigin
public class ControllerTest {
    private String BASE_URL = "http://localhost:8080/menu/";


    @GetMapping("")
    public Dish getMenu(){
        Dish dish = new Dish();
        dish.setTitle("Чикен Макнаггетс (9 шт.)");
        dish.setDescription("Неподражаемые Чикен Макнаггетс 9 шт. - это сочное 100% белое куриное мясо в хрустящей панировке со специями. Только натуральная курочка без искусственных красителей и ароматизаторов и без консервантов.");
        dish.setId_img(1);
        return dish;
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
