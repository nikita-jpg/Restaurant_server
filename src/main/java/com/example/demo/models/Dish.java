package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


/**
 * Класс описывает модель "Блюдо" ".
 * Содержит свойства <b>id</b>, <b>title</b>, <b>description</b>.
 */
@Getter
@Setter
@Entity
@Table(name = "DISHES")
public class Dish {

    /** Хранит id блюда*/
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "dishesIdSeq", sequenceName = "dishes_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dishesIdSeq")
    private int id;

    /** Хранит название блюда*/
    @Column(name = "title")
    private String title;

    /** Хранит описание блюда*/
    @Column(name = "description")
    private String description;

}
