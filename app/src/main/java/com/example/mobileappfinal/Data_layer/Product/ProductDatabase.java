package com.example.mobileappfinal.Data_layer.Product;

import com.example.mobileappfinal.DTO.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDatabase {
    private static ProductDatabase instance;
    private List<Product> productList;

    private ProductDatabase() {
        productList = new ArrayList<>();
        fetchProductList();
    }

    public static synchronized ProductDatabase getInstance() {
        if (instance == null) {
            instance = new ProductDatabase();
        }
        return instance;
    }

    public void fetchProductList(){
        List<Product> productResponses = new ArrayList<>();

        productResponses.add(new Product("Cartus 1", "$18", "plant", true));
        productResponses.add(new Product("Cartus 1", "$18", "plant", true));
        productResponses.add(new Product("Cartus 1", "$18", "plant", true));
        productResponses.add(new Product("Cartus 1", "$18", "plant", true));
        productResponses.add(new Product("Cartus 2", "$18", "plant", false));
        productResponses.add(new Product("Cartus 2", "$18", "plant", false));


        for (Product product : productResponses){
            productList.add(product);
        }
    }

    public List<Product> getProductList() {
        return productList;
    }
}
