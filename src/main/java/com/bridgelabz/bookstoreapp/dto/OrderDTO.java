package com.bridgelabz.bookstoreapp.dto;

import lombok.Data;

import java.time.LocalDate;
public @Data class OrderDTO {

    public LocalDate date;
    public int price;
    public int quantity;
    public String address;
    public long userid;
    public long bookid;
    boolean cancel;
}
