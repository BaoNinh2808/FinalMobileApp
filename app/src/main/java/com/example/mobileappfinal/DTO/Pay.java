package com.example.mobileappfinal.DTO;

import java.util.Date;

public class Pay {
    private String product_id;
    private String user_id;
    private int quantity;
    private Date date;

    public Pay(String productId, String userId, int quantity, Date date) {
        this.product_id = productId;
        this.user_id = userId;
        this.quantity = quantity;
        this.date = date;
    }


    public Pay(){
        this.product_id = "";
        this.user_id = "";
        this.quantity = 1;
        this.date = new Date();
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
