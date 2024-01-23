package com.example.mobileappfinal.Data_layer.Cart;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.mobileappfinal.DTO.Favorite;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FavoriteDatabase {
    private FirebaseFirestore db;

    private FirebaseAuth mAuth;

    private List<Favorite> favoriteList;

    private MutableLiveData<List<Favorite>> favoriteListLiveData = new MutableLiveData<>();

    public FavoriteDatabase(){
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        favoriteList = new ArrayList<>();
    }


    public void addFavoriteItem(Map<String, Object> FavoriteItem){
        db.collection("Favorite")
                .add(FavoriteItem)
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

    public void removeFavoriteItem(String productId) {
        for (Favorite favorite : favoriteList) {
            if (favorite.getProduct_id().equals(productId) && favorite.getUser_id().equals(mAuth.getCurrentUser().getUid())) {
                db.collection("Favorite")
                        .document(favorite.getId())
                        .delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    // Sau khi xoá thành công, cập nhật lại danh sách và LiveData
                                    favoriteList.remove(favorite);
                                    favoriteListLiveData.postValue(favoriteList);
                                } else {
                                    Exception e = task.getException();
                                    if (e != null) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        });
                break;
            }
        }
    }

    public void fetchFavoriteList(){
        db.collection("Favorite")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            favoriteList.clear();
                            for (QueryDocumentSnapshot document: task.getResult()){
                                if (mAuth.getCurrentUser().getUid().equals(document.getString("user_id"))){
                                    Favorite favorite = new Favorite();
                                    favorite.setId(document.getId());
                                    favorite.setProduct_id(document.getString("product_id"));
                                    favorite.setUser_id(document.getString("user_id"));

                                    favoriteList.add(favorite);
                                }
                            }

                            favoriteListLiveData.postValue(favoriteList);
                        } else {
                            Exception e = task.getException();
                            if (e != null) {
                                e.printStackTrace();
                            }
                        }

                    }
                });
    }

    public MutableLiveData<List<Favorite>> getFavoriteListLiveData(){
        return favoriteListLiveData;
    }


}
