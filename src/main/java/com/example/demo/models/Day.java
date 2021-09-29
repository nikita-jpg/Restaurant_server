package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Класс описывает модель "День" ".
 * Содержит свойства <b>tableId</b> и <b>freeTime</b>.
 */
@Getter
@Setter
public class Day {

    /** Хранит id столика*/
    private int tableId;

    /** Хранит List, содержащий список свободных промежутков времени для этого столика*/
    private List<String> freeTime;
}
