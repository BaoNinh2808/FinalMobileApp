package com.example.mobileappfinal.Business_layer.Object3D;

import com.example.mobileappfinal.DTO.Object3D;
import com.example.mobileappfinal.Data_layer.Object3D.Object3DDatabase;

import java.util.ArrayList;
import java.util.List;

public class Object3DListManager {
    private Object3DDatabase object3DDatabase;

    public Object3DListManager() {
        object3DDatabase = Object3DDatabase.getInstance();
    }

    public List<Object3D> getObject3DList() {
        return object3DDatabase.getObject3DList();
    }

    public Object3D getObject3DById(String id) {
        return object3DDatabase.getObject3DById(id);
    }

}
