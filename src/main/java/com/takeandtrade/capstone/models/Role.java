package com.takeandtrade.capstone.models;

import javax.persistence.*;

@Entity
@Table
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 50)
    private String roleType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
