package com.takeandtrade.capstone.models;

import javax.persistence.*;

@Entity
@Table
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 5)
    private int rating;





}
