package com.example.mobileappfinal.GUI_layer.Product;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mobileappfinal.Business_layer.product.SpecifyProductManager;
import com.example.mobileappfinal.R;

public class ProductActivity extends AppCompatActivity {

    private SpecifyProductManager specifyProductManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
    }

}