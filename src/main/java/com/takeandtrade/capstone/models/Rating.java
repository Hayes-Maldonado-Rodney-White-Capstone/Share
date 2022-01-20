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

    public Rating(long id, int rating) {
        this.id = id;
        this.rating = rating;
    }

    public Rating(int rating) {
        this.rating = rating;
    }

    public Rating(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
