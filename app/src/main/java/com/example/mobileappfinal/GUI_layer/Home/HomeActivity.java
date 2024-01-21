package com.example.mobileappfinal.GUI_layer.Home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileappfinal.Business_layer.Product.ProductListManager;
import com.example.mobileappfinal.DTO.Product;
import com.example.mobileappfinal.GUI_layer.Product.ProductActivity;
import com.example.mobileappfinal.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private ProductListManager productListManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        productListManager = new ProductListManager();

        setAndGetAllView();
        setHotProductList();

        FloatingActionButton favorite = findViewById(R.id.favorite);
        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProductActivity.class);
                // I want to pass a product name to intent
                intent.putExtra("name","Cartus 1" );
                startActivity(intent);
            }
        });
    }

    private void setHotProductList() {
        List<Product> productList = productListManager.getHotProductList();

        productAdapter = new ProductAdapter(this, productList);
        recyclerView.setAdapter(productAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    private void setAndGetAllView() {
        recyclerView = findViewById(R.id.recyclerViewProduct);
    }
}
