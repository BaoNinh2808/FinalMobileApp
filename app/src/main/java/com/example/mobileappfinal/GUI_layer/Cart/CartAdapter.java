package com.example.mobileappfinal.GUI_layer.Cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileappfinal.Business_layer.Product.ProductListManager;
import com.example.mobileappfinal.DTO.CartItem;
import com.example.mobileappfinal.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<CartItem> cartItemList;

    private ProductListManager productListManager;
    private Context context;

    public CartAdapter(Context context, List<CartItem> cartItemList) {
        this.context = context;
        this.cartItemList = cartItemList;

        productListManager = new ProductListManager();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem cartItem = cartItemList.get(position);

        String Name = productListManager.getProductById(cartItem.getProduct_id()).getName();
        String Price = productListManager.getProductById(cartItem.getProduct_id()).getPrice();
        String imageUrl = productListManager.getProductById(cartItem.getProduct_id()).getImageUrl();

        holder.textViewProductName.setText(Name);
        holder.textViewProductPrice.setText(Price);
        Picasso.get().load(imageUrl).into(holder.imageViewProductImage);

    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView textViewProductName;
        TextView textViewProductPrice;

        ImageView imageViewProductImage;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewProductName = itemView.findViewById(R.id.tvNameProductCartItem);
            textViewProductPrice = itemView.findViewById(R.id.tvPriceProductCartItem);
            imageViewProductImage = itemView.findViewById(R.id.ivProductCartItem);

        }
    }
}

