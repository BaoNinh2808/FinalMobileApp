package com.example.mobileappfinal.DTO;

public class Favorite {
    private String user_id;
    private String product_id;

    private String id;

    public Favorite(String user_id, String product_id, String id) {
        this.user_id = user_id;
        this.product_id = product_id;
        this.id = id;
    }

    public Favorite(){
        this.user_id = "";
        this.product_id = "";
        this.id = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }
}
