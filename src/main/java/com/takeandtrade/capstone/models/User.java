package com.takeandtrade.capstone.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 255)
    private String firstName;

    @Column(nullable = false, length = 255)
    private String lastName;

    @Column (nullable = false, length = 255)
    private String username;

    @Column (nullable = false)
    @DateTimeFormat(pattern ="mm-dd-yyyy")
    private String dateOfBirth;


    @Column (nullable = false, length = 255)
    private String email;

    @Column (nullable = false, length = 255)
    private String password;

    @Column (nullable = false)
    private long phoneNumber;

    @Column (nullable = false, length = 255)
    private String city;

    @Column (nullable = false, length = 2)
    private String state;

    @Column (nullable = false)
    private int zipCode;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

//    @ManyToOne
//    @JoinColumn (name = "role_id")
//    private User role;

    public User(User copy) {
        id = copy.id;
        email = copy.email;
        username = copy.username;
        password = copy.password;
    }

    public User(){}

    public User(long id, String firstName, String lastName, String username, String dateOfBirth, String email, String password, long phoneNumber, String city, String state, int zipCode) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    @OneToOne(mappedBy = "userzip")
    private Item item;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Item> items;
}
