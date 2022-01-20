package com.takeandtrade.capstone.models;

import javax.persistence.*;
import java.util.List;


@Entity
@Table
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 50)
    private String reviewTitle;

    @Column(nullable = false, length = 1000)
    private String reviewBody;

    @ManyToMany(cascade =  CascadeType.ALL)
    @JoinTable(
            name = "users_reviews",
            joinColumns={@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name="review_id")}
    )
    private List<User> userReviews;


}
