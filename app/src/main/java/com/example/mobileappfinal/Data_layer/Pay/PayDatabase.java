package com.example.mobileappfinal.Data_layer.Pay;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.mobileappfinal.DTO.CartItem;
import com.example.mobileappfinal.DTO.Pay;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PayDatabase {
    FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private List<Pay> payList;

    private MutableLiveData<List<Pay>> payListLiveData = new MutableLiveData<>();

    public PayDatabase() {
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        payList = new ArrayList<>();
    }

    public void addPay(Map<String, Object> Pay) {
        // Tạo một reference đến collection "cart_items"
        db.collection("Pay")
                .add(Pay)
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

    public void fetchPayItemList(){

        db.collection("Pay")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            payList.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (mAuth.getCurrentUser().getUid().equals(document.getString("user_id"))){
                                    Pay pay = new Pay();
                                    pay.setProduct_id(document.getString("product_id"));
                                    pay.setUser_id(document.getString("user_id"));

                                    if (document.contains("quantity")) {
                                        Long quantity = document.getLong("quantity");
                                        if (quantity != null) {
                                            pay.setQuantity(quantity.intValue());
                                        }
                                    }

                                    if (document.contains("date")) {
                                        Timestamp timestamp = document.getTimestamp("date");
                                        if (timestamp != null) {
                                            pay.setDate(timestamp.toDate());
                                        }
                                    }

                                    payList.add(pay);
                                }
                            }

                            payListLiveData.postValue(payList);

                        } else {
                            Exception e = task.getException();
                            if (e != null) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    public MutableLiveData<List<Pay>> getPayListLiveData () {
        return payListLiveData;
    }
}
