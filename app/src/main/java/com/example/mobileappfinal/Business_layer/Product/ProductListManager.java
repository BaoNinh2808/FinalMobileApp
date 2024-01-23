package com.example.mobileappfinal.Business_layer.Product;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.mobileappfinal.DTO.Product;
import com.example.mobileappfinal.Data_layer.Product.ProductDatabase;

import java.util.ArrayList;
import java.util.List;

public class ProductListManager {

    private ProductDatabase productDatabase;

    public ProductListManager() {
        productDatabase = ProductDatabase.getInstance();
    }

    public LiveData<List<Product>> getProductListLiveData() {
        return productDatabase.getProductListLiveData();
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

    public List<Product> getSimilarProductList(String productCategory) {
        List<Product> allProducts = productDatabase.getProductList();
        List<Product> similarProducts = new ArrayList<>();

        for (Product product : allProducts) {
            if (product.getCategory().equals(productCategory)) {
                similarProducts.add(product);
            }
        }

        return similarProducts;
    }

    public Product getProductById(String id) {
        return productDatabase.getProductById(id);
    }
}
