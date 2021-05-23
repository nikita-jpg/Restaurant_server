package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@SpringBootApplication
public class DemoApplication {
    //Указывает на путь к папке с картинками
    public final static String PATH_IMG = "C:\\Users\\Nikita\\Desktop\\images\\";
//    public final static String DATE_FORMAT = ;
    public final static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd", Locale.getDefault());
    public final static DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss", Locale.getDefault());
    public final static LocalTime OPEN_TIME_WORKING_WEEK = LocalTime.parse("09:00:00",TIME_FORMATTER);
    public final static LocalTime CLOSE_TIME_WORKING_WEEK = LocalTime.parse("23:00:00",TIME_FORMATTER);
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
