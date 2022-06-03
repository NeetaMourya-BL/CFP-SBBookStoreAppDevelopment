package com.bridgelabz.bookstoreapp.dto;

public class BookDTO {
    public String bookName;
    public String autherName;
    public String bookDescription;
    public String bookImg;
    public int price;
    public int quantity;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAutherName() {
        return autherName;
    }

    public void setAutherName(String autherName) {
        this.autherName = autherName;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public String getBookImg() {
        return bookImg;
    }

    public void setBookImg(String bookImg) {
        this.bookImg = bookImg;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BookDTO(String bookName, String autherName, String bookDescription, String bookImg, int price, int quantity) {
        this.bookName = bookName;
        this.autherName = autherName;
        this.bookDescription = bookDescription;
        this.bookImg = bookImg;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "bookName='" + bookName + '\'' +
                ", autherName='" + autherName + '\'' +
                ", bookDescription='" + bookDescription + '\'' +
                ", bookImg='" + bookImg + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
