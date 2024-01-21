package com.example.mobileappfinal.Data_layer.product;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;

import com.example.mobileappfinal.DTO.Product;
import com.example.mobileappfinal.R;

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

        String cactusUrl = "https://drive.google.com/file/d/1V2cQCw2Cs2fF4Uli9Uybqa49-SiUXEsW/view?usp=sharing";

        productResponses.add(new Product(
                1,
                "Cartus 1",
                "$18",
                cactusUrl,
                "plant",
                true,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod, nisl eget maximus ultrices, nunc nisl ultricies arcu, quis aliquam nisl nunc eu nisl. Sed vitae nisl eget nisl aliquam ultrices. Sed vitae nisl eget nisl aliquam ultrices.",
                10,
                "4.5",
                15,
                10));
        productResponses.add(new Product(
                2,
                "Cartus 2",
                "$18",
                cactusUrl,
                "plant",
                true,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod, nisl eget maximus ultrices, nunc nisl ultricies arcu, quis aliquam nisl nunc eu nisl. Sed vitae nisl eget nisl aliquam ultrices. Sed vitae nisl eget nisl aliquam ultrices.",
                10,
                "4.5",
                15,
                10));
        productResponses.add(new Product(
                3,
                "Cartus 3",
                "$18",
                cactusUrl,
                "plant",
                true,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod, nisl eget maximus ultrices, nunc nisl ultricies arcu, quis aliquam nisl nunc eu nisl. Sed vitae nisl eget nisl aliquam ultrices. Sed vitae nisl eget nisl aliquam ultrices.",
                10,
                "4.5",
                15,
                10));
        productResponses.add(new Product(
                4,
                "Cartus 4",
                "$18",
                cactusUrl,
                "plant",
                true,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod, nisl eget maximus ultrices, nunc nisl ultricies arcu, quis aliquam nisl nunc eu nisl. Sed vitae nisl eget nisl aliquam ultrices. Sed vitae nisl eget nisl aliquam ultrices.",
                10,
                "4.5",
                15,
                10));
        productResponses.add(new Product(
                5,
                "Cartus 5",
                "$18",
                cactusUrl,
                "plant",
                true,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod, nisl eget maximus ultrices, nunc nisl ultricies arcu, quis aliquam nisl nunc eu nisl. Sed vitae nisl eget nisl aliquam ultrices. Sed vitae nisl eget nisl aliquam ultrices.",
                10,
                "4.5",
                15,
                10));
        productResponses.add(new Product(
                6,
                "Cartus 6",
                "$18",
                cactusUrl,
                "plant",
                true,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod, nisl eget maximus ultrices, nunc nisl ultricies arcu, quis aliquam nisl nunc eu nisl. Sed vitae nisl eget nisl aliquam ultrices. Sed vitae nisl eget nisl aliquam ultrices.",
                10,
                "4.5",
                15,
                10));
        productResponses.add(new Product(
                7,
                "Cartus 7",
                "$18",
                cactusUrl,
                "plant",
                true,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod, nisl eget maximus ultrices, nunc nisl ultricies arcu, quis aliquam nisl nunc eu nisl. Sed vitae nisl eget nisl aliquam ultrices. Sed vitae nisl eget nisl aliquam ultrices.",
                10,
                "4.5",
                15,
                10));
        productResponses.add(new Product(
                8,
                "Cartus 8",
                "$18",
                cactusUrl,
                "plant",
                true,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod, nisl eget maximus ultrices, nunc nisl ultricies arcu, quis aliquam nisl nunc eu nisl. Sed vitae nisl eget nisl aliquam ultrices. Sed vitae nisl eget nisl aliquam ultrices.",
                10,
                "4.5",
                15,
                10));
        productResponses.add(new Product(
                9,
                "Cartus 9",
                "$18",
                cactusUrl,
                "plant",
                true,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod, nisl eget maximus ultrices, nunc nisl ultricies arcu, quis aliquam nisl nunc eu nisl. Sed vitae nisl eget nisl aliquam ultrices. Sed vitae nisl eget nisl aliquam ultrices.",
                10,
                "4.5",
                15,
                10));

        for (Product product : productResponses){
            productList.add(product);
        }
    }

    public List<Product> getProductList() {
        return productList;
    }

    public Product getProductById(int id){
        for (Product product : productList){
            if (product.getId() == id){
                return product;
            }
        }
        return null;
    }
    public Product getProductByName(String name){
        for (Product product : productList){
            if (product.getName().equals(name)){
                return product;
            }
        }
        return null;
    }

}
