package com.example.mobileappfinal.Data_layer.Object3D;

import com.example.mobileappfinal.DTO.Object3D;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class Object3DDatabase {
    FirebaseFirestore db;
    private static Object3DDatabase instance;
    private List<Object3D> object3DList;

    private Object3DDatabase() {
        object3DList = new ArrayList<>();
        fetchObject3DList();
    }

    public static synchronized Object3DDatabase getInstance() {
        if (instance == null) {
            instance = new Object3DDatabase();
        }
        return instance;
    }

    private void fetchObject3DList() {
        db = FirebaseFirestore.getInstance();
        db.collection("product_obj_and_texture")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        object3DList.clear();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Object3D tempObject3D = new Object3D();
                            tempObject3D.setObjUrl(document.getString("objUrl"));
                            tempObject3D.setTextureUrl(document.getString("textureUrl"));
                            tempObject3D.setProductName(document.getString("name"));
                            tempObject3D.setProductId(document.getString("id"));
                            object3DList.add(tempObject3D);
                        }
                    }
                    else {
                        Exception e = task.getException();
                        if (e != null) {
                            e.printStackTrace();
                        }
                    }

                });
    }

    public List<Object3D> getObject3DList() {
        return object3DList;
    }

    public List<Object3D> getObject3DListByCategory(String category) {
        List<Object3D> result = new ArrayList<>();
        for (Object3D object3D : object3DList) {
            if (object3D.getProductId().contains(category)) {
                result.add(object3D);
            }
        }
        return result;
    }

    public Object3D getObject3DById(String id) {
        for (Object3D object3D : object3DList) {
            if (object3D.getProductId().equals(id)) {
                return object3D;
            }
        }
        return null;
    }
}
