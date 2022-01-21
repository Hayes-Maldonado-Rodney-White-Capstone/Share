package com.takeandtrade.capstone.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

@Entity
@Table
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @DateTimeFormat(pattern ="mm-dd-yyyy")
    private String startDate;

    @Column
    @DateTimeFormat(pattern ="mm-dd-yyyy")
    private String endDate;

    //add a many to one relationship, one user can have many requests

}
