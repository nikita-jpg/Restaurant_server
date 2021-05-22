package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
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
    private int deskNumber;

    @Column(name = "date")
    private LocalDate date;

//    @Column(name = "start")
//    private String start;
//
//    @Column(name = "end")
//    private String end;


}
