package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "DESKS")
public class Desk {
    @Id
    @Column(name = "number")
    private int tableNumber;

    @OneToMany(mappedBy = "desk")
    private List<Record> records;

}
