package com.example.mobileappfinal.GUI_layer.Cart;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileappfinal.DTO.CartItem;
import com.example.mobileappfinal.Data_layer.Cart.CartDatabase;
import com.example.mobileappfinal.Data_layer.Pay.PayDatabase;
import com.example.mobileappfinal.GUI_layer.Home.HomeActivity;
import com.example.mobileappfinal.R;
import com.google.firebase.auth.FirebaseAuth;

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
        observeCartItemList();
        setEventClickButtonPay();
    }

    private void setEventClickButtonPay() {
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Build the alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
                builder.setTitle("Confirmation");
                builder.setMessage("Are you sure you want to proceed with the payment?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // User clicked Yes, proceed with the payment
                        performPayment();
                        Intent intent = new Intent(CartActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // User clicked No, do nothing or handle accordingly
                        dialog.dismiss();
                    }
                });

                // Show the alert dialog
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    private void performPayment() {
        // Execute the payment logic here
        LocalDate currentDate = LocalDate.now();
        Date date = java.sql.Date.valueOf(currentDate.toString());

        for (CartItem selectedItem : selectedItems) {
            // Create a Pay object for each selected item
            Map<String, Object> pay = new HashMap<>();
            pay.put("product_id", selectedItem.getProduct_id());
            pay.put("user_id", mAuth.getCurrentUser().getUid());
            pay.put("quantity", selectedItem.getQuantity());
            pay.put("date", date);

            // Add the Pay object to the database
            payDatabase.addPay(pay);
            // Delete the corresponding CartItem
            cartDatabase.deleteCartItem(selectedItem.getProduct_id());
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
