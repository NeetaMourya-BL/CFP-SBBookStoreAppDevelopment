package com.bridgelabz.bookstoreapp.model;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;
@Entity //use for create database and table using @Data we don't need to define any getter setter contructor and to string method
@Table(name = "orderdb")
public @Data class OrderData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderID")
    private long orderID;
    @Column(name = "date")
    public LocalDate date;
    private int price;
    private int quantity;
    private String address;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserRegistrationData user;

    @ManyToOne
    @JoinColumn(name = "id")
    private BookData book;

    private boolean cancle;

    public OrderData() {
    }

    public OrderData(Integer price, Integer quantity, String address, BookData book, UserRegistrationData user, boolean cancel) {
        this.date = LocalDate.now();
        this.price = price;
        this.quantity = quantity;
        this.address = address;
        this.user = user;
        this.book =book;

    }
}