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



    public Review(long id, String reviewTitle, String reviewBody, User producer, User consumer, Rating rating) {
        this.id = id;
        this.reviewTitle = reviewTitle;
        this.reviewBody = reviewBody;
        this.producer = producer;
        this.consumer = consumer;
        this.rating = rating;
    }

    public Review(String reviewTitle, String reviewBody, User producer, User consumer, Rating rating) {
        this.reviewTitle = reviewTitle;
        this.reviewBody = reviewBody;
        this.producer = producer;
        this.consumer = consumer;
        this.rating = rating;
    }

    public Review(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getReviewTitle() {
        return reviewTitle;
    }

    public void setReviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
    }

    public String getReviewBody() {
        return reviewBody;
    }

    public void setReviewBody(String reviewBody) {
        this.reviewBody = reviewBody;
    }

    public User getProducer() {
        return producer;
    }

    public void setProducer(User producer) {
        this.producer = producer;
    }

    public User getConsumer() {
        return consumer;
    }

    public void setConsumer(User consumer) {
        this.consumer = consumer;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    @ManyToOne
    @JoinColumn(name = "producer_id")//user that is publishing the post/item to share
    public User producer;

    @ManyToOne
    @JoinColumn(name = "consumer_id")// user that is borrowed the item/writes the review/ and is login
    public User consumer;




    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rating_id") //this should create a foreign key in the review table
    private Rating rating;




}
