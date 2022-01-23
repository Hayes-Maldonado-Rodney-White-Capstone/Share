package com.takeandtrade.capstone.models;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.util.Collection;
import java.util.Date;

@Entity
@Table
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @DateTimeFormat(pattern ="yyyy-mm-dd")
    private Date beginDate;

    @Column
    @DateTimeFormat(pattern ="yyyy-mm-dd")
    private Date dueDate;

    @Column
    private String approver1;  //the lender is approver 1. the approver is the person who posted the item. setapprover to the username that posted the item.  so we can use item.getUser.getusername

    //do I need a status, like status Open means the approver hasn't approved the request yet?
    @Column
    private String status = "OPEN";

    @Column(length = 500)
    private String notes;

    //will need to add Many to Many mapping between users and items. and a getter to get the logged in user/consumer
    //I'll need a relationship between the user_id who is submitting the request form. Many users can make many requests?
    //will need to get the producers id (the person who posted the item and is the approver), the consumers id(the person who is borrowing the item and is making the request), and the item id
//    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "requests")
//    private Collection<Item> requesteditems;

    //many requests for one item?
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")
    private Item itemReq;

    //one user can make many requests
    @ManyToOne()
    @JoinColumn(name = "user_id") //the FK will be on the request table
    private User userReq;

    public Request() {
    }

    public Request(long id, Date beginDate, Date dueDate, String approver1, String status, String notes, User userReq) {
        this.id = id;
        this.beginDate = beginDate;
        this.dueDate = dueDate;
        this.approver1 = approver1;
        this.status = status;
        this.notes = notes;
        this.userReq = userReq;
    }

    public Request(Date beginDate, Date dueDate, String approver1, String status, String notes, User userReq) {
        this.beginDate = beginDate;
        this.dueDate = dueDate;
        this.approver1 = approver1;
        this.status = status;
        this.notes = notes;
        this.userReq = userReq;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getApprover1() {
        return approver1;
    }

    public void setApprover1(String approver1) {
        this.approver1 = approver1;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public User getUserReq() {
        return userReq;
    }

    public void setUserReq(User userReq) {
        this.userReq = userReq;
    }

    public Item getItemReq() {
        return itemReq;
    }

    public void setItemReq(Item itemReq) {
        this.itemReq = itemReq;
    }
}
