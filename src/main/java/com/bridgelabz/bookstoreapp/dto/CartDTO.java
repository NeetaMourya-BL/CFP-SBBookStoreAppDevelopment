package com.bridgelabz.bookstoreapp.dto;

public class CartDTO {
    public long userid;
    public long bookid;
    public int quantity;

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public long getBookid() {
        return bookid;
    }

    public void setBookid(long bookid) {
        this.bookid = bookid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public CartDTO(long userid, long bookid, int quantity) {
        this.userid = userid;
        this.bookid = bookid;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartDTO{" +
                "userid='" + userid + '\'' +
                ", bookid='" + bookid + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
