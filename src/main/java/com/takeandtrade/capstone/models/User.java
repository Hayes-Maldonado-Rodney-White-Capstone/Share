package com.takeandtrade.capstone.models;

import com.mysql.cj.Messages;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
    @NotBlank(message = "First name can't be blank")
    @Size(min = 1, message = "First name must be at least 1 character")
    private String firstName;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "Last name can't be blank")
    @Size(min = 1, message = "Last name must be at least 1 character")
    private String lastName;

    @Column (nullable = false, length = 50, unique = true)
    @NotBlank(message = "username can't be blank")
    @Size(min = 4, message = "username must be at least 4 characters")
    private String username;

    @Column (nullable = false)
    @DateTimeFormat(pattern ="MM-dd-yyyy")
    private String dateOfBirth;

    @Column (nullable = false, length = 255, unique = true)
    @Email(message = "Invalid email. Try again")
    private String email;

    @Column (nullable = false, length = 255)
    @NotBlank(message = "password can't be blank")
    @Size(min = 4, message = "password must be at least 4 characters")
    private String password;

    @Column (nullable = false)
    private long phoneNumber;

    @Column (nullable = false, length = 50)
    private String city;

    @Column (nullable = false, length = 2)
    private String state;

    @Column (nullable = false)
    @Min(value = 78201, message = "enter your 5 digit zip code between 78201 and 78250")
    private int zipcode;

    @Column(nullable = false)
    private String profileimage;   //string because we are saving the name here

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

    public String getProfileimage() {
        return profileimage;
    }

    public void setProfileimage(String profileimage) {
        this.profileimage = profileimage;
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
        profileimage = copy.profileimage;
    }

    public User(){}

    public User(long id, String firstName, String lastName, String username, String dateOfBirth, String email, String password, long phoneNumber, String city, String state, int zipcode, String profileimage) {
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
        this.profileimage = profileimage;
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

    //one user can make many requests
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userReq")
    private List<Request> request;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "consumer")//user that is borrowed the item/writes the review/ and is login
    private List<Review> writtenReviews;

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

    public List<Request> getRequest() {
        return request;
    }

    public void setRequest(List<Request> request) {
        this.request = request;
    }

    //for file upload
    @Transient
    public String getProfilePhotoImagePath() {
        if (profileimage == null) return null;

        return "/images/capstoneimages/" + profileimage;
    }

}
