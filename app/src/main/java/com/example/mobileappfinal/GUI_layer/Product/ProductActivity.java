package com.example.mobileappfinal.GUI_layer.Product;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobileappfinal.Business_layer.Product.ProductListManager;
import com.example.mobileappfinal.Business_layer.Product.SpecifyProductManager;
import com.example.mobileappfinal.DTO.Product;
import com.example.mobileappfinal.GUI_layer.Home.ProductAdapter;
import com.example.mobileappfinal.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductActivity extends AppCompatActivity {

    private SpecifyProductManager specifyProductManager;
    private RecyclerView recyclerViewSimilarProducts;
    private ProductAdapter productAdapter;

    private ProductListManager productListManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        specifyProductManager = new SpecifyProductManager();
        productListManager = new ProductListManager();

        recyclerViewSimilarProducts = findViewById(R.id.recyclerViewSimilarProducts);

        // get product name from intent
        String productName = getIntent().getStringExtra("name");
        Log.d("ProductActivity", "onCreate: " + productName);
        // get product from product name
        Product product = specifyProductManager.getProductByName(productName);
        Log.d("ProductActivity", "onCreate 1: " + product.getName());
        initialView(product);
        initialRecyclerViewSimilarProducts(recyclerViewSimilarProducts, product);
    }

    private void initialRecyclerViewSimilarProducts(RecyclerView recyclerView, Product product) {
        List<Product> productList = productListManager.getSimilarProductList(product.getCategory());

        productAdapter = new ProductAdapter(this, productList);
        recyclerView.setAdapter(productAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    private void initialView(Product product) {
        ImageView imageViewProduct = findViewById(R.id.imageViewProduct);
        Picasso.get().load(product.getImageUrl()).into(imageViewProduct);
        Log.d("ProductActivity", "initialView: " + product.getImageUrl());

        TextView textViewProductName = findViewById(R.id.txtProductName);
        textViewProductName.setText(product.getName());
        Log.d("ProductActivity", "initialView: " + product.getName());

        TextView textViewProductPrice = findViewById(R.id.txtProductPrice);
        textViewProductPrice.setText(product.getPrice() + " VND");
        Log.d("ProductActivity", "initialView: " + product.getPrice() + " VND");

        TextView textViewProductDescription = findViewById(R.id.txtProductDescription);
        textViewProductDescription.setText(product.getDescription());
        Log.d("ProductActivity", "initialView: " + product.getDescription());


        TextView textViewProductCategory = findViewById(R.id.txtCategoryProduct);
        textViewProductCategory.setText(product.getCategory());
        Log.d("ProductActivity", "initialView: " + product.getCategory());

        TextView textViewNumberOfQuantity = findViewById(R.id.txtAvailableAmount);
        textViewNumberOfQuantity.setText(String.valueOf(product.getAvailableAmount()));
        Log.d("ProductActivity", "initialView: " + product.getAvailableAmount());

        TextView textViewSoldProduct = findViewById(R.id.txtSoldAmount);
        textViewSoldProduct.setText(String.valueOf(product.getSoldAmount()));
        Log.d("ProductActivity", "initialView: " + product.getSoldAmount());
    }

}