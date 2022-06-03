package com.bridgelabz.bookstoreapp.model;

import com.bridgelabz.bookstoreapp.dto.BookDTO;
import com.bridgelabz.bookstoreapp.dto.CartDTO;
import lombok.Data;
import javax.persistence.*;

@Entity //use for create database and table using @Data we don't need to define any getter setter contructor and to string method
@Table(name = "cartdb")
public @Data class CartData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartid")
    private long cartid;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserRegistrationData user;

    @ManyToOne
    @JoinColumn(name = "id")
    private BookData book;
    private int quantity;

    public CartData(CartDTO cartDTO) {
        this.updateCartbyID(cartDTO);
        this.updateQuantity(cartDTO);
    }

    public CartData() {

    }
    public CartData(int quantity, BookData book, UserRegistrationData user) {
        this.cartid = cartid;
        this.book = book;
        this.user = user;
        this.quantity = quantity;
    }
    public CartData(int quantity) {
        this.quantity = quantity;
    }

    public void updateCartbyID(CartDTO cartDTO) {
//       this.user = cartDTO.userid;
//       this.book = cartDTO.bookid;
        this.quantity = cartDTO.quantity;
    }

    public void updateQuantity(CartDTO cartDTO) {
        this.quantity = cartDTO.quantity;
    }
}