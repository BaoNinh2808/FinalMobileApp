package com.example.mobileappfinal.GUI_layer.Product;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mobileappfinal.Business_layer.Product.ProductListManager;
import com.example.mobileappfinal.DTO.Product;
import com.example.mobileappfinal.Data_layer.Cart.CartDatabase;
import com.example.mobileappfinal.GUI_layer.Cart.CartActivity;
import com.example.mobileappfinal.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class CartBottomSheetFragment extends BottomSheetDialogFragment {

    private Button btnNavToCardActivity;

    private ImageView productImage;
    private TextView productPrice, productName, productQuantity;

    private ImageView btnMinus, btnPlus;
    private Product currentProduct;
    private String currentProductID;
    private ProductListManager productListManager;

    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart_bottom_sheet, container, false);

        productListManager = new ProductListManager();
        mAuth = FirebaseAuth.getInstance();

        setCurrentProduct();
        setAndGetAllView(view);
        setEventClickButtonAdd();
        setEventMinusQuantity();
        setEventPlusQuantity();

        return view;
    }

    private void setEventPlusQuantity() {
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int prodQuantity = Integer.valueOf(productQuantity.getText().toString());
                prodQuantity = prodQuantity + 1;

                productQuantity.setText(String.valueOf(prodQuantity));
            }
        });
    }

    private void setEventMinusQuantity() {
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int prodQuantity = Integer.valueOf(productQuantity.getText().toString());

                if (prodQuantity > 1) {
                    prodQuantity = prodQuantity - 1;
                }

                productQuantity.setText(String.valueOf(prodQuantity));
            }
        });
    }

    private void setEventClickButtonAdd() {

        btnNavToCardActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCartItemToDB();

                Intent intent = new Intent(getActivity(), CartActivity.class);

                startActivity(intent);
            }
        });
    }

    private void addCartItemToDB() {
        CartDatabase cartDatabase = new CartDatabase();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        String user_id = currentUser.getUid();

        int quantity = Integer.valueOf(productQuantity.getText().toString());


        Map<String, Object> cartItem = new HashMap<>();
        cartItem.put("product_id", currentProductID);
        cartItem.put("user_id", user_id);
        cartItem.put("quantity", quantity);

        cartDatabase.addCartItem(cartItem);

    }

    private void setAndGetAllView(View view) {
        btnNavToCardActivity = view.findViewById(R.id.btnAddAndNavCart);
        productImage = view.findViewById(R.id.ivProductCart);
        productPrice = view.findViewById(R.id.tvPriceProductCart);
        productName = view.findViewById(R.id.tvNameProductCart);
        productQuantity = view.findViewById(R.id.tvQuantity);
        btnMinus = view.findViewById(R.id.minusQuantityButton);
        btnPlus = view.findViewById(R.id.plusQuantityButton);

        Picasso.get().load(currentProduct.getImageUrl()).into(productImage);
        productPrice.setText(currentProduct.getPrice());
        productName.setText(currentProduct.getName());

    }

    private void setCurrentProduct() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            currentProductID = bundle.getString("product_id", "");
            currentProduct = productListManager.getProductById(currentProductID);
        }
    }
}
