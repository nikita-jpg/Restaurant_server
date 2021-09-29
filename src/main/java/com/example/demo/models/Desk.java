package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

/**
 * Класс описывает модель "Столик" ".
 * Содержит свойство <b>deskNumber</b>.
 */
@Getter
@Setter
@Entity
@Table(name = "DESKS")
public class Desk {

    /** Хранит номер столика*/
    @Id
    @Column(name = "number")
    private int deskNumber;

}
