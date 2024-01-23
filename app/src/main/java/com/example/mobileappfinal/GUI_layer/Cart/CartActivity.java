package com.example.mobileappfinal.GUI_layer.Cart;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileappfinal.DTO.CartItem;
import com.example.mobileappfinal.Data_layer.Cart.CartDatabase;
import com.example.mobileappfinal.R;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    private CartDatabase cartDatabase;
    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartDatabase = new CartDatabase();
        cartDatabase.fetchCartItemList();

        setAndGetAllView();
        observeCartItemList();
    }

    private void observeCartItemList() {
        cartDatabase.getCartItemListLiveData().observe(this, new Observer<List<CartItem>>() {
            @Override
            public void onChanged(List<CartItem> cartItems) {
                Log.d("TESTTEST", String.valueOf(cartItems.size()));
                setCartItemList(cartItems);
            }
        });
    }

    private void setCartItemList(List<CartItem> cartItems) {
        cartAdapter = new CartAdapter(this, cartItems);

        recyclerView.setAdapter(cartAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(CartActivity.this));
    }


    private void setAndGetAllView() {
        recyclerView = findViewById(R.id.rvCartItemList);
    }
}
