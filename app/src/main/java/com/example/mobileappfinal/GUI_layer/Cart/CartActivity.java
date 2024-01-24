package com.example.mobileappfinal.GUI_layer.Cart;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileappfinal.DTO.CartItem;
import com.example.mobileappfinal.Data_layer.Cart.CartDatabase;
import com.example.mobileappfinal.Data_layer.Pay.PayDatabase;
import com.example.mobileappfinal.GUI_layer.Categories.MainCategoriesActivity;
import com.example.mobileappfinal.GUI_layer.Home.HomeActivity;
import com.example.mobileappfinal.GUI_layer.Pay.PaymentActivity;
import com.example.mobileappfinal.GUI_layer.SignIn.SignInActivity;
import com.example.mobileappfinal.GUI_layer.User.UserActivity;
import com.example.mobileappfinal.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartActivity extends AppCompatActivity {
    private CartDatabase cartDatabase;
    private PayDatabase payDatabase;

    private FirebaseAuth mAuth;

    private Button btnPay;
    private TextView totalAmount;
    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private int totalPrice = 0;

    private List<CartItem> selectedItems;

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    private ImageView closeDrawer;
    private ImageView iconNav;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        mAuth = FirebaseAuth.getInstance();

        cartDatabase = new CartDatabase();
        cartDatabase.fetchCartItemList();

        selectedItems = new ArrayList<>();

        payDatabase = new PayDatabase();

        setAndGetAllView();
        menuNavigation();
        setNavIconClick();
        observeCartItemList();
        setEventClickButtonPay();
    }

    private void setNavIconClick() {
        iconNav = findViewById(R.id.icon_nav_cart);
        iconNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void menuNavigation() {
        drawerLayout = findViewById(R.id.drawer_layout_cart);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // Initialize NavigationView and set its item click listener
        navigationView = findViewById(R.id.navigation_view_cart);
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


    private void setEventClickButtonPay() {
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performPayment();
            }
        });
    }

    private void performPayment() {
        // Execute the payment logic here
        LocalDate currentDate = LocalDate.now();
        Date date = java.sql.Date.valueOf(currentDate.toString());

        List<Map<String, Object>> pays = new ArrayList<>();
        List<String> product_ids = new ArrayList<>();

        for (CartItem selectedItem : selectedItems) {
            // Create a Pay object for each selected item
            Map<String, Object> pay = new HashMap<>();
            pay.put("product_id", selectedItem.getProduct_id());
            pay.put("user_id", mAuth.getCurrentUser().getUid());
            pay.put("quantity", selectedItem.getQuantity());
            pay.put("date", date);

            pays.add(pay);
            product_ids.add(selectedItem.getProduct_id());

            Intent intent = new Intent(CartActivity.this, PaymentActivity.class);
            intent.putExtra("pays", (Serializable) pays);
            intent.putStringArrayListExtra("product_ids", new ArrayList<>(product_ids));

            startActivity(intent);

        }

        // You can also update the UI or perform other actions after payment
        // ...
    }


    private void observeCartItemList() {
        cartDatabase.getCartItemListLiveData().observe(this, new Observer<List<CartItem>>() {
            @Override
            public void onChanged(List<CartItem> cartItems) {
                setCartItemList(cartItems);
            }
        });
    }

    private void setCartItemList(List<CartItem> cartItems) {
        cartAdapter = new CartAdapter(this, cartItems);
        cartAdapter.setOnCheckedChangeListener(new CartAdapter.OnCheckedChangeListener() {
            @Override
            public void onItemCheckedChanged(int position, boolean isChecked, int quantity, int price) {
                if (isChecked){
                    totalPrice += quantity * price;
                    CartItem selectedCartItem = cartItems.get(position);
                    selectedItems.add(selectedCartItem);
                } else {
                    totalPrice -= quantity * price;
                    CartItem unselectedCartItem = cartItems.get(position);
                    selectedItems.remove(unselectedCartItem);
                }
                totalAmount.setText(String.valueOf(totalPrice) + "$");
            }

        });



        recyclerView.setAdapter(cartAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(CartActivity.this));
    }


    private void setAndGetAllView() {
        recyclerView = findViewById(R.id.rvCartItemList);
        totalAmount = findViewById(R.id.tvTotalPrice);
        btnPay = findViewById(R.id.btnPay);
    }
}
