package com.example.mobileappfinal.GUI_layer.Home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileappfinal.Business_layer.Product.ProductListManager;
import com.example.mobileappfinal.DTO.Product;
import com.example.mobileappfinal.GUI_layer.Cart.CartActivity;
import com.example.mobileappfinal.GUI_layer.Product.ProductActivity;
import com.example.mobileappfinal.GUI_layer.User.UserActivity;
import com.example.mobileappfinal.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ImageView iconNav;
    private ImageView cartLogo;
    private ProductAdapter productAdapter;
    private ProductListManager productListManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        productListManager = new ProductListManager();

        setAndGetAllView();
        observeProductList();
        setEventClickButtonCart();
        setEventClickIconNav();
    }

    private void setEventClickIconNav() {
        iconNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, UserActivity.class);
                startActivity(intent);
            }
        });
    }


    private void setEventClickButtonCart() {
        cartLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
    }

    private void observeProductList() {
        productListManager.getProductListLiveData().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> productList) {
                List<Product> hotProducts = new ArrayList<>();

                for (Product product : productList) {
                    if (product.getHot()) {
                        hotProducts.add(product);
                    }
                }
                setHotProductList(hotProducts);
            }
        });
    }

    private void setHotProductList(List<Product> productList) {
        productAdapter = new ProductAdapter(this, productList);

        productAdapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Product product) {
                Intent intent = new Intent(getApplicationContext(), ProductActivity.class);
                // I want to pass a product name to intent

                intent.putExtra("product_id", product.getId());
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(productAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    private void setAndGetAllView() {
        cartLogo = findViewById(R.id.ivCartLogo);
        recyclerView = findViewById(R.id.recyclerViewProduct);
        iconNav = findViewById(R.id.iconNavPage);
    }
}

