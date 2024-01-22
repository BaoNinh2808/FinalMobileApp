package com.example.mobileappfinal.Data_layer.Product;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.mobileappfinal.DTO.Product;
import com.example.mobileappfinal.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class ProductDatabase {
    private FirebaseFirestore db;
    private static ProductDatabase instance;
    private List<Product> productList;

    private MutableLiveData<List<Product>> productListLiveData = new MutableLiveData<>();

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
        db = FirebaseFirestore.getInstance();

        db.collection("product")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            productList.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Product tempProduct = new Product();
                                tempProduct.setId(document.getId());
                                tempProduct.setName(document.getString("name"));
                                tempProduct.setPrice(document.getString("price"));
                                tempProduct.setImageUrl(document.getString("imageUrl"));
                                tempProduct.setCategory(document.getString("category"));
                                tempProduct.setHot(document.getBoolean("isHot"));
                                tempProduct.setAvailableAmount(document.getLong("available").intValue());
                                tempProduct.setSoldAmount(document.getLong("sold").intValue());

                                productList.add(tempProduct);
                            }

                            productListLiveData.postValue(productList);

                        } else {
                            Exception e = task.getException();
                            if (e != null) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

    }

    public MutableLiveData<List<Product>> getProductListLiveData() {
        return productListLiveData;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public Product getProductById(String id){
        for (Product product : productList){
            if (product.getId().equals(id)){
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
