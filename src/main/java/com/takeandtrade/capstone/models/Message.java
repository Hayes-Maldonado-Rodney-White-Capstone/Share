package com.takeandtrade.capstone.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 1000) //may need to update this to TEXT within mysql
    private String message;

    @Column(nullable = false)
    @DateTimeFormat(pattern ="mm-dd-yyyy hh:mm")
    private LocalDateTime timeSent;  //in mySQL, DATETIME NOT NULL default CURRENT_TIMESTAMP. Need to research this too, do I need an annotation, a getter/setter etc

    @ManyToOne
    @JoinColumn(name = "sender_id")
    public User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    public User receiver;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimeSent() {
        return timeSent;
    }

    public void setTimeSent(LocalDateTime timeSent) {
        this.timeSent = timeSent;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }



    public Message() {

    }

    public Message(long id, String message, LocalDateTime timeSent, User sender, User receiver) {
        this.id = id;
        this.message = message;
        this.timeSent = timeSent;
        this.sender = sender;
        this.receiver = receiver;
    }

    public Message(String message, LocalDateTime timeSent, User sender, User receiver) {
        this.message = message;
        this.timeSent = timeSent;
        this.sender = sender;
        this.receiver = receiver;
    }

}
