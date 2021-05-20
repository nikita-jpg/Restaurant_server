package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "DISHES")
public class Dish {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "diseshesIdSeq", sequenceName = "dises_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "disesIdSeq")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;

}
