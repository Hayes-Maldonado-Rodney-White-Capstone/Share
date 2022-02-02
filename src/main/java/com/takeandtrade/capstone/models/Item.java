package com.takeandtrade.capstone.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
    @NotBlank(message = "Title can't be blank")
    @Size(min = 2, message = "Title must be at least 2 characters")
    private String itemName;

    @Column(nullable = false, length = 1000)
    @NotBlank(message = "Description can't be blank")
    @Size(min = 2, message = "Description must be at least 2 characters")
    private String itemDescription;

    @Column(nullable = true, length = 1000)
    private String specialInstructions;

    @Column(nullable = false)
    @NotBlank(message = "Choose the condition")
    private String itemCondition;

    @Column(nullable = true)
    private double price;  //in mysql this can be DECIMAL(6,2) which allows prices up to 9999.99

    @Column(nullable = false)
    @NotBlank(message = "Upload a file under 5MB")
    private String image;   //file name

    @Column(nullable = false)
    private boolean availability;

    @Column(nullable = true, length = 500)
    private String personalizedTermsAndConditions;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime datePosted = LocalDateTime.now();  //in mySQL, DATETIME NOT NULL default CURRENT_TIMESTAMP.

    //one item has one category
    @OneToOne()
    @JoinColumn(name = "category_id")
    private Category category;

    //one user can have many items-- many items can have one user
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "itemReq", cascade = CascadeType.ALL)
    List<Request> requests;

    //default constructor
    public Item() {
    }

    public Item(long id, String itemName, String itemDescription, String specialInstructions, String itemCondition, double price, String image, boolean availability, String personalizedTermsAndConditions, LocalDateTime datePosted) {
        this.id = id;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.specialInstructions = specialInstructions;
        this.itemCondition = itemCondition;
        this.price = price;
        this.image = image;
        this.availability = availability;
        this.personalizedTermsAndConditions = personalizedTermsAndConditions;
        this.datePosted = datePosted;
    }

    public Item(String itemName, String itemDescription, String specialInstructions, String itemCondition, double price, String image, boolean availability, String personalizedTermsAndConditions, LocalDateTime datePosted) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.specialInstructions = specialInstructions;
        this.itemCondition = itemCondition;
        this.price = price;
        this.image = image;
        this.availability = availability;
        this.personalizedTermsAndConditions = personalizedTermsAndConditions;
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

    public String getPersonalizedTermsAndConditions() {return personalizedTermsAndConditions;}

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

    public void setPersonalizedTermsAndConditions(String personalizedTermsAndConditions) {this.personalizedTermsAndConditions = personalizedTermsAndConditions;}

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
