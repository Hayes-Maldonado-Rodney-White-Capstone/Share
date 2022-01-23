package com.takeandtrade.capstone.models;

import com.mysql.cj.Messages;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column (nullable = false, length = 50)
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

    @Column (nullable = false, length = 50)
    private String city;

    @Column (nullable = false, length = 2)
    private String state;

    @Column (nullable = false)
    private int zipcode;

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

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public User(User copy) {
        id = copy.id;
        email = copy.email;
        username = copy.username;
        password = copy.password;
        firstName = copy.firstName;
        lastName = copy.lastName;
        dateOfBirth = copy.dateOfBirth;
        phoneNumber = copy.phoneNumber;
        city = copy.city;
        state = copy.state;
        zipcode = copy.zipcode;
    }

    public User(){}

    public User(long id, String firstName, String lastName, String username, String dateOfBirth, String email, String password, long phoneNumber, String city, String state, int zipcode) {
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
        this.zipcode = zipcode;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Item> items;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sender")
    private List<Message> sentMessages;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receiver")
    private List<Message> receivedMessages;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producer")//user that is publishing the post/item to share
    private List<Review> reviewed;

    @ManyToMany()
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    public List<Message> getSentMessages() {
        return sentMessages;
    }

    public void setSentMessages(List<Message> sentMessages) {
        this.sentMessages = sentMessages;
    }

    public List<Message> getReceivedMessages() {
        return receivedMessages;
    }

    public void setReceivedMessages(List<Message> receivedMessages) {
        this.receivedMessages = receivedMessages;
    }

    public List<Review> getReviewed() {
        return reviewed;
    }

    public void setReviewed(List<Review> reviewed) {
        this.reviewed = reviewed;
    }

    public List<Review> getWrittenReviews() {
        return writtenReviews;
    }

    public void setWrittenReviews(List<Review> writtenReviews) {
        this.writtenReviews = writtenReviews;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "consumer")//user that is borrowed the item/writes the review/ and is login
    private List<Review> writtenReviews;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
