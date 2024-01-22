package com.example.mobileappfinal.GUI_layer.Product;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobileappfinal.Business_layer.Product.ProductListManager;
import com.example.mobileappfinal.Business_layer.Product.SpecifyProductManager;
import com.example.mobileappfinal.DTO.Product;
import com.example.mobileappfinal.GUI_layer.Home.ProductAdapter;
import com.example.mobileappfinal.R;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Stack;

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
        // get product from product name
        Product product = specifyProductManager.getProductByName(productName);
        initialView(product);
        initialRecyclerViewSimilarProducts(recyclerViewSimilarProducts, product);
        handleOnclick();
    }

    private void handleOnclick() {
        handleBackButton();
        handleFavouriteButton();
        handleTestWithARButton();
        handleChangeQuantityButton();
        handleAddToCartButton();
    }

    private void handleChangeQuantityButton() {
        ImageView imageButtonIncreaseValue = findViewById(R.id.imageButtonIncreaseValue);
        ImageView imageButtonDecreaseValue = findViewById(R.id.imageButtonDecreaseValue);
        TextView textViewSelectedProduct = findViewById(R.id.txtNumberSelectedProduct);

        TextView textViewAvailableAmount = findViewById(R.id.txtAvailableAmount);
        int availableAmount = Integer.parseInt(textViewAvailableAmount.getText().toString());

        imageButtonIncreaseValue.setOnClickListener(v -> {
            int numberOfQuantity = Integer.parseInt(textViewSelectedProduct.getText().toString());
            if (numberOfQuantity < availableAmount) {
                numberOfQuantity++;
                textViewSelectedProduct.setText(String.valueOf(numberOfQuantity));
            }
        });

        imageButtonDecreaseValue.setOnClickListener(v -> {
            int numberOfQuantity = Integer.parseInt(textViewSelectedProduct.getText().toString());
            if (numberOfQuantity > 1) {
                numberOfQuantity--;
                textViewSelectedProduct.setText(String.valueOf(numberOfQuantity));
            }
        });
    }

    private void handleAddToCartButton() {
        //TODO: Cài đặt giỏ hàng
    }

    private void handleTestWithARButton() {
        //TODO: handle test with AR button

    }

    private void handleFavouriteButton() {
        ImageView imageViewFavorite = findViewById(R.id.imageViewFavorite);

//        TODO: Nho lay tu firebase

        final boolean[] isFavorite = {false}; // Using an array to make it effectively final

        imageViewFavorite.setOnClickListener(v -> {
            if (isFavorite[0]) {
                // Remove favorite (toggle off)
                imageViewFavorite.setImageDrawable(getResources().getDrawable(R.drawable.icon_favorite_border, null));
            } else {
                // Set favorite (toggle on)
                imageViewFavorite.setImageDrawable(getResources().getDrawable(R.drawable.icon_favorite, null));
            }

            // Toggle the state
            isFavorite[0] = !isFavorite[0];
        });

    }

    private void handleBackButton() {
        ImageView imageViewBackProduct = findViewById(R.id.imageViewBackProduct);
        imageViewBackProduct.setOnClickListener(v -> {
            finish();
        });
    }

    private void initialRecyclerViewSimilarProducts(RecyclerView recyclerView, Product product) {
        List<Product> productList = productListManager.getSimilarProductList(product.getCategory());

        productAdapter = new ProductAdapter(this, productList);
        //handle click on similar product of recycler view
        productAdapter.setOnItemClickListener(p -> {
            Intent intent = new Intent(this, ProductActivity.class);
            intent.putExtra("name", p.getName());
            startActivity(intent);
        });
        recyclerView.setAdapter(productAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    private void initialView(Product product) {
        //TODO: nhớ lấy isfavourite từ firebase để set icon

        ImageView imageViewProduct = findViewById(R.id.imageViewProduct);
        Picasso.get().load(product.getImageUrl()).into(imageViewProduct);

        TextView textViewProductName = findViewById(R.id.txtProductName);
        textViewProductName.setText(product.getName());

        TextView textViewProductPrice = findViewById(R.id.txtProductPrice);
        textViewProductPrice.setText(product.getPrice());

        TextView textViewProductDescription = findViewById(R.id.txtProductDescription);
        textViewProductDescription.setText(product.getDescription());

        TextView textViewProductCategory = findViewById(R.id.txtCategoryProduct);
        textViewProductCategory.setText(product.getCategory());

        TextView textViewNumberOfQuantity = findViewById(R.id.txtAvailableAmount);
        textViewNumberOfQuantity.setText(String.valueOf(product.getAvailableAmount()));

        TextView textViewSoldProduct = findViewById(R.id.txtSoldAmount);
        textViewSoldProduct.setText(String.valueOf(product.getSoldAmount()));
    }

}