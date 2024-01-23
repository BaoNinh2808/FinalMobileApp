package com.example.mobileappfinal.GUI_layer.User;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileappfinal.Business_layer.Product.ProductListManager;
import com.example.mobileappfinal.DTO.Favorite;
import com.example.mobileappfinal.R;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {

    private List<Favorite> favoriteList;

    private Context context;
    private ProductListManager productListManager;

    public FavoriteAdapter(Context context, List<Favorite> favoriteList){
        this.context = context;
        this.favoriteList = favoriteList;

        productListManager = new ProductListManager();
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_item, parent, false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {

        Favorite favorite = favoriteList.get(position);

        String Name = productListManager.getProductById(favorite.getProduct_id()).getName();
        String Price = productListManager.getProductById(favorite.getProduct_id()).getPrice();
        String imageUrl = productListManager.getProductById(favorite.getProduct_id()).getImageUrl();

        holder.textViewProductName.setText(Name);
        holder.textViewProductPrice.setText(Price);
        Picasso.get().load(imageUrl).into(holder.imageViewProductImage);

    }

    @Override
    public int getItemCount() {
        return favoriteList.size();
    }

    public static class FavoriteViewHolder extends RecyclerView.ViewHolder {
        TextView textViewProductName;
        TextView textViewProductPrice;
        ImageView imageViewProductImage;

        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewProductName = itemView.findViewById(R.id.tvNameProductFavoriteItem);
            textViewProductPrice = itemView.findViewById(R.id.tvPriceProductFavoriteItem);
            imageViewProductImage = itemView.findViewById(R.id.ivProductFavoriteItem);

        }
    }
}
