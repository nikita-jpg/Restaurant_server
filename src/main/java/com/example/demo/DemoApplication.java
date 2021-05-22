package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

@SpringBootApplication
public class DemoApplication {
    //Указывает на путь к папке с картинками
    public final static String PATH_IMG = "C:\\Users\\Nikita\\Desktop\\images\\";
//    public final static String DATE_FORMAT = ;
    public final static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy.MM.dd", Locale.getDefault());
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
