package com.example.mobileappfinal.GUI_layer.Cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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

    public interface OnCheckedChangeListener {
        void onItemCheckedChanged(int position, boolean isChecked, int quantity, int price);
    }


    private OnCheckedChangeListener onCheckedChangeListener;

    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        this.onCheckedChangeListener = listener;
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
        int Quantity = cartItem.getQuantity();


        holder.textViewProductName.setText(Name);
        holder.textViewProductPrice.setText(Price);
        holder.textViewQuantity.setText(String.valueOf(Quantity));
        Picasso.get().load(imageUrl).into(holder.imageViewProductImage);

        int intPrice = Integer.parseInt(Price.replaceAll("[^0-9]", ""));

        holder.imageViewMinusQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int prodQuantity = Integer.valueOf(holder.textViewQuantity.getText().toString());

                if (prodQuantity > 1) {
                    prodQuantity = prodQuantity - 1;
                }

                holder.textViewQuantity.setText(String.valueOf(prodQuantity));
            }
        });

        holder.imageViewPlusQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int prodQuantity = Integer.valueOf(holder.textViewQuantity.getText().toString());
                prodQuantity = prodQuantity + 1;

                holder.textViewQuantity.setText(String.valueOf(prodQuantity));
            }
        });

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (onCheckedChangeListener != null) {
                    if (isChecked) {
                        holder.imageViewMinusQuantity.setClickable(false);
                        holder.imageViewPlusQuantity.setClickable(false);
                    } else {
                        holder.imageViewMinusQuantity.setClickable(true);
                        holder.imageViewPlusQuantity.setClickable(true);
                    }
                    onCheckedChangeListener.onItemCheckedChanged(position, isChecked, Integer.valueOf(holder.textViewQuantity.getText().toString()), intPrice);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView textViewProductName;
        TextView textViewProductPrice;
        ImageView imageViewProductImage;

        ImageView imageViewMinusQuantity;
        ImageView imageViewPlusQuantity;
        TextView textViewQuantity;

        CheckBox checkBox;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewProductName = itemView.findViewById(R.id.tvNameProductCartItem);
            textViewProductPrice = itemView.findViewById(R.id.tvPriceProductCartItem);
            imageViewProductImage = itemView.findViewById(R.id.ivProductCartItem);

            imageViewMinusQuantity = itemView.findViewById(R.id.minusQuantityButtonCart);
            imageViewPlusQuantity = itemView.findViewById(R.id.plusQuantityButtonCart);
            textViewQuantity = itemView.findViewById(R.id.tvQuantityCart);

            checkBox = itemView.findViewById(R.id.cbCartItem);
        }
    }
}

