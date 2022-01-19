package com.takeandtrade.capstone.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

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
    private String image;   //in mySQL this will be a BLOB. I need to research whether it should be a String here.

    @Column(nullable = false)  //would be nice if it automatically defaults to true (the item is available) can be data type TINYINT in mysql
    private boolean availability;

    @Column(nullable = false)
    @DateTimeFormat(pattern ="mm-dd-yyyy")
    private LocalDateTime datePosted;  //in mySQL, DATETIME NOT NULL default CURRENT_TIMESTAMP. Need to research this too, do I need an annotation, a getter/setter etc

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

    @Transient
    public String getPhotosImagePath() {
        if (image == null) return null;

        return "/capstoneimages/" + image;
    }

    //one item has one category
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY) //adding in cascade and orphanRemoval and fetch bc I got this error More than one row with the given identifier was found: 4
    @JoinColumn(name = "category_id") //this should create a foreign key in the Item table
    private Category category;

    //we'll need to add a getter (I think) after adding the relationship
    //do I need a constructor similar to this one below, or does it need every field?:
    //public Item(String name, Category category) {
    // this.name = name;
    // this.category = category;
    // this.category.setItem(this);
    // }


    //one user can have many items-- many items can have one user
    @ManyToOne
    @JoinColumn(name = "user_id")  //this should create a FK in the Item table
    private User user;

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
