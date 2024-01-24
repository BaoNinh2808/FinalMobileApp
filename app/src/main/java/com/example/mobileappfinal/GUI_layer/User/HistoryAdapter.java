package com.example.mobileappfinal.GUI_layer.User;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileappfinal.Business_layer.Product.ProductListManager;
import com.example.mobileappfinal.DTO.Favorite;
import com.example.mobileappfinal.DTO.Pay;
import com.example.mobileappfinal.R;
import com.google.api.ResourceDescriptor;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>{

    private List<Pay> payList;

    private Context context;

    private ProductListManager productListManager;

    public HistoryAdapter(Context context, List<Pay> payList) {
        this.payList = payList;
        this.context = context;

        this.productListManager = new ProductListManager();
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_item, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        Pay pay = payList.get(position);

        String Name = productListManager.getProductById(pay.getProduct_id()).getName();
        String Price = productListManager.getProductById(pay.getProduct_id()).getPrice();

        String numericString = Price.replaceAll("[^0-9]", "");

        int intPrice = Integer.parseInt(numericString);
        int Quantity = pay.getQuantity();
        int totalPrice = intPrice * Quantity;

        String imageUrl = productListManager.getProductById(pay.getProduct_id()).getImageUrl();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String dateString = sdf.format(pay.getDate());

        holder.textViewProductName.setText(Name);
        holder.textViewProductPrice.setText(String.valueOf(totalPrice) + "$");
        holder.textViewPaymentDate.setText(dateString);
        Picasso.get().load(imageUrl).into(holder.imageViewProductImage);
    }

    @Override
    public int getItemCount() {
        return payList.size();
    }

    public static class HistoryViewHolder extends RecyclerView.ViewHolder {
        TextView textViewProductName;
        TextView textViewProductPrice;
        TextView textViewPaymentDate;
        ImageView imageViewProductImage;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewProductName = itemView.findViewById(R.id.tvNameProductPaymentItem);
            textViewProductPrice = itemView.findViewById(R.id.tvPriceProductPaymentItem);
            imageViewProductImage = itemView.findViewById(R.id.ivProductPaymentItem);
            textViewPaymentDate = itemView.findViewById(R.id.tvDateProductPaymentItem);

        }
    }

}
