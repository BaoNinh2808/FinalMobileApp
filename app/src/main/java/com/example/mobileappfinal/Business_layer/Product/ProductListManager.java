package com.example.mobileappfinal.Business_layer.product;

import com.example.mobileappfinal.DTO.Product;
import com.example.mobileappfinal.Data_layer.product.ProductDatabase;

import java.util.ArrayList;
import java.util.List;

public class ProductListManager {

    private ProductDatabase productDatabase;
    public ProductListManager() {
        productDatabase = ProductDatabase.getInstance();
    }

    public List<Product> getHotProductList() {
        List<Product> allProducts = productDatabase.getProductList();
        List<Product> hotProducts = new ArrayList<>();

        for (Product product : allProducts) {
            if (product.getHot()) {
                hotProducts.add(product);
            }
        }

        return hotProducts;
    }
}
