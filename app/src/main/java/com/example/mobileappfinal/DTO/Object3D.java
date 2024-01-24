package com.example.mobileappfinal.DTO;

public class Object3D {
    private String ObjUrl;
    private String textureUrl;
    private String productName;
    private String productId;

    public Object3D(String ObjUrl, String textureUrl, String productName, String productId) {
        this.ObjUrl = ObjUrl;
        this.textureUrl = textureUrl;
        this.productName = productName;
        this.productId = productId;
    }

    public Object3D() {
        this.ObjUrl = "";
        this.textureUrl = "";
        this.productName = "";
        this.productId = "";
    }

    public String getObjUrl() {
        return ObjUrl;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getTextureUrl() {
        return textureUrl;
    }

    public void setObjUrl(String objUrl) {
        ObjUrl = objUrl;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setTextureUrl(String textureUrl) {
        this.textureUrl = textureUrl;
    }
}
