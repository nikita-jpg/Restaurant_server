package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

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

    @Column(name = "start_date")
    private LocalDate date;

    @Column(name = "start_time")
    private LocalTime start;

    @Column(name = "end_time")
    private LocalTime end;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "client_second_name")
    private String clientSecondName;

    @Column(name = "client_tel_number")
    private String clientTelNumber;

}
