package com.takeandtrade.capstone.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 255)
    private String itemName;

    @Column(nullable = false, length = 9000) //may need to update this to TEXT within mysql
    private String itemDescription;

    @Column(nullable = true, length = 9000) //may need to update this to TEXT within mysql
    private String specialInstructions;

    @Column(nullable = false)
    private String condition;  //this will be a checkbox on the form, excellent, good, fair, poor

    @Column(nullable = true)
    private double price;  //in mysql this can be DECIMAL(6,2) which allows prices up to 9999.99

    @Column(nullable = false)
    private String image;   //in mySQL this will be a BLOB. I need to research whether it should be a String here.

    @Column(nullable = false, columnDefinition = "Boolean default true")  //this will automatically default to true (the item is available) can be data type TINYINT in mysql
    private Boolean available;

    @Column(nullable = false)
    private Date datePosted;  //in mySQL, DATETIME NOT NULL default CURRENT_TIMESTAMP. Need to research this too, do I need an annotation, a getter/setter etc


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
