package com.example.mobileappfinal.Data_layer.Cart;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mobileappfinal.DTO.CartItem;
import com.example.mobileappfinal.DTO.Product;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CartDatabase {
    private FirebaseFirestore db;

    private FirebaseAuth mAuth;

    private List<CartItem> cartItemList;

    private MutableLiveData<List<CartItem>> cartItemListLiveData = new MutableLiveData<>();

    public CartDatabase() {
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        cartItemList = new ArrayList<>();
    }



    public void addCartItem(Map<String, Object> cartItem) {
        // Tạo một reference đến collection "cart_items"
        db.collection("cartItem")
                .add(cartItem)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }

    public void deleteCartItem(String productId) {
        Log.d("DELETE_PRODUCT_ID", productId);
        CollectionReference cartItemsRef = db.collection("cartItem");

        Query query = cartItemsRef.whereEqualTo("product_id", productId);

        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        DocumentReference documentReference = document.getReference();
                        documentReference.delete();
                    }
                } else {
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            }
        });
    }


    public void fetchCartItemList(){

        db.collection("cartItem")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            cartItemList.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (mAuth.getCurrentUser().getUid().equals(document.getString("user_id"))){
                                    CartItem cartItem = new CartItem();
                                    cartItem.setId(document.getId());
                                    cartItem.setProduct_id(document.getString("product_id"));
                                    cartItem.setUser_id(document.getString("user_id"));

                                    Long quantityLong = document.getLong("quantity");
                                    if (quantityLong != null) {
                                        int quantity = quantityLong.intValue();
                                        cartItem.setQuantity(quantity);
                                    }

                                    cartItemList.add(cartItem);
                                }
                            }

                            cartItemListLiveData.postValue(cartItemList);

                        } else {
                            Exception e = task.getException();
                            if (e != null) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

    }

    public MutableLiveData<List<CartItem>> getCartItemListLiveData () {
        return cartItemListLiveData;
    }

    public CartItem getCartItemById(String id){
        for (CartItem cartItem :cartItemList){
            if (cartItem.getId().equals(id)){
                return cartItem;
            }
        }
        return null;
    }
}
