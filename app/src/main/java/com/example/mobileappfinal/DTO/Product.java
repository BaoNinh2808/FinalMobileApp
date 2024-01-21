package com.example.mobileappfinal.DTO;

public class Product {
    private String name;
    private String price;
    private String category;
    private Boolean isHot;
    private int id;

    public Product(String name, String price, String category, Boolean isHot) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.isHot = isHot;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getHot() {
        return isHot;
    }

    public void setHot(Boolean hot) {
        isHot = hot;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
