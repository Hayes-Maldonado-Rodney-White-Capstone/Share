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
    @NotEmpty(message = "field cannot be blank")
    @Future(message = "date must be in the future")
    @DateTimeFormat(pattern ="yyyy-mm-dd")
    private Date beginDate;

    @Column
    @DateTimeFormat(pattern ="yyyy-mm-dd")
    private Date dueDate;

    @Column
    private String approver1;  //the lender is approver 1. Might not need this field here. After we add mapping, the approver will be the person who posted the item, so we can use item.getUser I think

    //do I need a status, like status Open means the approver hasn't approved the request yet?
    private String status = "OPEN";

    //will need to add Many to Many mapping between users and items. and a getter to get the logged in user/consumer
    //I'll need a relationship between the user_id who is submitting the request form. Many users can make many requests?
    //will need to get the producers id (the person who posted the item and is the approver), the consumers id(the person who is borrowing the item and is making the request), and the item id
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "requests")
    private Collection<Item> requesteditems;


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

    public Collection<Item> getRequesteditems() {
        return requesteditems;
    }

    public void setRequesteditems(Collection<Item> requesteditems) {
        this.requesteditems = requesteditems;
    }
}
