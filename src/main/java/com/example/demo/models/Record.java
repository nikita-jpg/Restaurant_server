package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Класс описывает модель "Бронь" ".
 * Содержит свойства <b>id</b>, <b>title</b>, <b>description</b>.
 */
@Getter
@Setter
@Entity
@Table(name = "RECORDS")
public class Record {

    /** Хранит id брони*/
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "recordsIdSeq", sequenceName = "records_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recordsIdSeq")
    private int id;

    /** Хранит номер забронированного столика*/
    @Column(name = "desk_number")
    private int deskNumber;

    /** Хранит день брони
     * @see com.example.demo.DemoApplication#DATE_FORMATTER
     */
    @Column(name = "start_date")
    private LocalDate date;

    /** Хранит время начала брони
     * @see com.example.demo.DemoApplication#TIME_FORMATTER
     */
    @Column(name = "start_time")
    private LocalTime start;

    /** Хранит время конца брони
     * @see com.example.demo.DemoApplication#TIME_FORMATTER
     */
    @Column(name = "end_time")
    private LocalTime end;

    /** Хранит имя клиента, забронировавшего столик*/
    @Column(name = "client_name")
    private String clientName;

    /** Хранит фамилию клиента, забронировавшего столик*/
    @Column(name = "client_second_name")
    private String clientSecondName;

    /** Хранит номер клиента, забронировавшего столик*/
    @Column(name = "client_tel_number")
    private String clientTelNumber;

}
