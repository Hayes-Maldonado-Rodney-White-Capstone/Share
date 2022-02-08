package com.takeandtrade.capstone.models;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 1000)
    private String content;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime timeSent;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    public User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    public User receiver;

    public Message() {

    }

    public Message(long id, String content, LocalDateTime timeSent, User sender, User receiver) {
        this.id = id;
        this.content = content;
        this.timeSent = timeSent;
        this.sender = sender;
        this.receiver = receiver;
    }

    public Message(String content, LocalDateTime timeSent, User sender, User receiver) {
        this.content = content;
        this.timeSent = timeSent;
        this.sender = sender;
        this.receiver = receiver;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

}
