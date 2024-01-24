package com.example.mobileappfinal.GUI_layer.Home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileappfinal.Business_layer.Product.ProductListManager;
import com.example.mobileappfinal.DTO.Product;
import com.example.mobileappfinal.GUI_layer.Cart.CartActivity;
import com.example.mobileappfinal.GUI_layer.Categories.MainCategoriesActivity;
import com.example.mobileappfinal.GUI_layer.Product.ProductActivity;
import com.example.mobileappfinal.GUI_layer.SignIn.SignInActivity;
import com.example.mobileappfinal.GUI_layer.User.UserActivity;
import com.example.mobileappfinal.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ImageView iconNav;
    private ImageView cartLogo;
    private ProductAdapter productAdapter;
    private ProductListManager productListManager;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    private ImageView closeDrawer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        productListManager = new ProductListManager();

        setAndGetAllView();
        menuNavigation();
        observeProductList();
        setEventClickButtonCart();
        setEventClickIconNav();
    }

    private void menuNavigation() {
        drawerLayout = findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // Initialize NavigationView and set its item click listener
        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_home) {
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    return true;
                } else if (item.getItemId() == R.id.menu_user) {
                    Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                    startActivity(intent);
                    return true;
                } else if (item.getItemId() == R.id.menu_cart) {
                    Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                    startActivity(intent);
                    return true;
                }
                else if (item.getItemId() == R.id.menu_category){
                    Intent intent = new Intent(getApplicationContext(), MainCategoriesActivity.class);
                    startActivity(intent);
                    return true;
                }
                else if (item.getItemId() == R.id.menu_logout) {
                    FirebaseAuth mAuth = FirebaseAuth.getInstance();
                    mAuth.signOut();
                    Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });

        // Accessing the button in the header and setting a click listener
        View headerView = navigationView.getHeaderView(0);
        closeDrawer = headerView.findViewById(R.id.closeDrawer);
        closeDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button click in the header
                drawerLayout.closeDrawer(GravityCompat.START);
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

