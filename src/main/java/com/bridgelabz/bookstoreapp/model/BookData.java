package com.bridgelabz.bookstoreapp.model;

import com.bridgelabz.bookstoreapp.dto.BookDTO;
import lombok.Data;
import javax.persistence.*;
@Entity //use for create database and table using @Data we don't need to define any getter setter contructor and to string method
@Table(name = "bookdb")
public @Data class BookData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    private String bookName;
    private String autherName;
    private String bookDescription;
    private String bookImg;
    private int price;
    private int quantity;

    public BookData(BookDTO bookDTO) {
        this.updateBookbyID(bookDTO);
        this.updateQuantity(bookDTO);
    }

    public BookData() {

    }

    public void updateBookbyID(BookDTO bookDTO) {
        this.bookName = bookDTO.bookName;
        this.autherName = bookDTO.autherName;
        this.bookDescription = bookDTO.bookDescription;
        this.bookImg = bookDTO.bookImg;
        this.price = bookDTO.price;
        this.quantity = bookDTO.quantity;
    }

    public void updateQuantity(BookDTO bookDTO) {
        this.quantity = bookDTO.quantity;
    }
}