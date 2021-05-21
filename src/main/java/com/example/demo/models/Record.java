package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "RECORDS")
public class Record {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "recordsIdSeq", sequenceName = "records_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recordsIdSeq")
    private int id;

//    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "desk_number")
    private int desk_number;

    @Column(name = "date")
    private Calendar calendar;


}
