package com.takeandtrade.capstone.models;

import javax.persistence.*;
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

    @Column(nullable = false)  //this will automatically default to true (the item is available) can be data type TINYINT in mysql
    private boolean availability;

    @Column(nullable = false)
    private Date datePosted;  //in mySQL, DATETIME NOT NULL default CURRENT_TIMESTAMP. Need to research this too, do I need an annotation, a getter/setter etc

    //default constructor
    public Item() {
    }

    //2 constructors, 1 with id and 1 without for insertion and extraction
    public Item(long id, String itemName, String itemDescription, String specialInstructions, String itemCondition, double price, String image, boolean availability, Date datePosted) {
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

    public Item(String itemName, String itemDescription, String specialInstructions, String itemCondition, double price, String image, boolean availability, Date datePosted) {
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

    public boolean getAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public Date getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(Date datePosted) {
        this.datePosted = datePosted;
    }

    //when we add mapping, one user can have many items, and many items can have one user
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;

//    //One item will have a zip code FK that is mapped to the user
//    @OneToOne
//    @JoinColumn(name = "user_zip")  //this will create the FK in the Item table
//    private User userzip;
//
    // we'll also need to map category after we set up the category table. one item has one category


//    //***in the User table we will need the following:
//    @OneToOne(mappedBy = "userzip")

//    //***in the User table, we need the following:
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
//    private List<Item> items;


}
