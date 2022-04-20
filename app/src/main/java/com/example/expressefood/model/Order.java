package com.example.expressefood.model;

public class Order {
    private String ProductID;
    private String ProductName;
    private String Qualitity;
    private String Price;
    private String Discount;

    public Order(String productID, String productName, String qualitity, String price, String discount) {
        ProductID = productID;
        ProductName = productName;
        Qualitity = qualitity;
        Price = price;
        Discount = discount;
    }

    public Order() {
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        ProductID = productID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getQualitity() {
        return Qualitity;
    }

    public void setQualitity(String qualitity) {
        Qualitity = qualitity;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }
}
