package com.takeandtrade.capstone.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 255)
    private String itemName;

    @Column(nullable = false, length = 1000) //may need to update this to TEXT within mysql
    private String itemDescription;

    @Column(nullable = true, length = 1000) //may need to update this to TEXT within mysql
    private String specialInstructions;

    @Column(nullable = false)
    private String itemCondition;  //this will be a checkbox on the form, excellent, good, fair, poor

    @Column(nullable = true)
    private double price;  //in mysql this can be DECIMAL(6,2) which allows prices up to 9999.99

    @Column(nullable = false)
    private String image;   //string because we are saving the name here

    @Column(nullable = false)  //would be nice if it automatically defaults to true (the item is available) can be data type TINYINT in mysql
    private boolean availability;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime datePosted = LocalDateTime.now();  //in mySQL, DATETIME NOT NULL default CURRENT_TIMESTAMP.

    //one item has one category
    @OneToOne()
    @JoinColumn(name = "category_id") //this should create a foreign key in the Item table
    private Category category;

    //one user can have many items-- many items can have one user
    @ManyToOne
    @JoinColumn(name = "user_id")  //this should create a FK in the Item table
    private User user;

    //many-to-many between Item and Request--I don't think this is correct...adding a one to many, one user can make many requests
//    @ManyToMany
//    @JoinTable(name = "item_request", joinColumns = @JoinColumn(name = "item_id"), inverseJoinColumns = @JoinColumn(name = "request_id"))
//    private Collection<Request> requests;

    //one item can have many requests?
    @OneToMany(mappedBy = "itemReq")
    List<Request> requests;

    //default constructor
    public Item() {
    }

    //2 constructors, 1 with id and 1 without for insertion and extraction
    public Item(long id, String itemName, String itemDescription, String specialInstructions, String itemCondition, double price, String image, boolean availability, LocalDateTime datePosted) {
        this.id = id;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.specialInstructions = specialInstructions;
        this.itemCondition = itemCondition;
        this.price = price;
        this.image = image;
        this.availability = availability;
        this.datePosted = datePosted;
    }

    public Item(String itemName, String itemDescription, String specialInstructions, String itemCondition, double price, String image, boolean availability, LocalDateTime datePosted) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.specialInstructions = specialInstructions;
        this.itemCondition = itemCondition;
        this.price = price;
        this.image = image;
        this.availability = availability;
        this.datePosted = datePosted;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    public String getItemCondition() {
        return itemCondition;
    }

    public void setItemCondition(String itemCondition) {
        this.itemCondition = itemCondition;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public LocalDateTime getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(LocalDateTime datePosted) {
        this.datePosted = datePosted;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    //for file upload
    @Transient
    public String getPhotosImagePath() {
        if (image == null) return null;

        return "/images/capstoneimages/" + image;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                ", specialInstructions='" + specialInstructions + '\'' +
                ", itemCondition='" + itemCondition + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", availability=" + availability +
                ", datePosted=" + datePosted +
                ", category=" + category +
                ", user=" + user +
                '}';
    }
}
