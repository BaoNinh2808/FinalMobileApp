package com.example.mobileappfinal.Business_layer.Product;

import com.example.mobileappfinal.DTO.Product;
import com.example.mobileappfinal.Data_layer.Product.ProductDatabase;

public class SpecifyProductManager {
    private ProductDatabase productDatabase;
    public SpecifyProductManager() {
        productDatabase = ProductDatabase.getInstance();
    }

    public Product getProductById(int id) {
        return productDatabase.getProductById(id);
    }

    public Product getProductByName(String name) {
        return productDatabase.getProductByName(name);
    }

}
