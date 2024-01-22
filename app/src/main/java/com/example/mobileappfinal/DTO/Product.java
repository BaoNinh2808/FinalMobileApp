package com.example.mobileappfinal.DTO;

public class Product {
    private String id;
    private String name;
    private String price;
    private String imageUrl;
    private String category;
    private Boolean isHot;
    private String description;
    private int discount;
    private String rate;
    private int availableAmount;
    private int soldAmount;

    //write constructor with all fields

    public Product(String id, String name, String price, String imageUrl, String category, Boolean isHot, String description, int availableAmount, int soldAmount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.category = category;
        this.isHot = isHot;
        this.description = description;
        this.discount = 10;
        this.rate = "4.5";
        this.availableAmount = availableAmount;
        this.soldAmount = soldAmount;
    }

    public Product(){
        this.id = "";
        this.name = "";
        this.price = "";
        this.imageUrl = "";
        this.category = "";
        this.isHot = false;
        this.description = "";
        this.discount = 10;
        this.rate = "4.5";
        this.availableAmount = 0;
        this.soldAmount = 0;
    }

    //write get & set methods for all fields

    public void setSoldAmount(int soldAmount) {
        this.soldAmount = soldAmount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public void setAvailableAmount(int availableAmount) {
        this.availableAmount = availableAmount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getAvailableAmount() {
        return availableAmount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getDiscount() {
        return discount;
    }

    public int getSoldAmount() {
        return soldAmount;
    }

    public String getRate() {
        return rate;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setRate(String rate) {
        this.rate = rate;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
