package com.takeandtrade.capstone.models;

import javax.persistence.*;

@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 255)
    private String first_name;

    @Column(nullable = false, length = 255)
    private String last_name;

    @Column (nullable = false, length = 255)
    private String username;

    @Column (nullable = false)
    private long date_of_birth;

    @Column (nullable = false, length = 255)
    private String email;

    @Column (nullable = false, length = 255)
    private String password;

    @Column (nullable = false)
    private int phone_number;

    @Column (nullable = false, length = 255)
    private String city;

    @Column (nullable = false, length = 2)
    private String state;

    @Column (nullable = false)
    private int zip_code;

//    @ManyToOne
//    @JoinColumn (name = "role_id")
//    private User role;




}
