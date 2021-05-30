package com.emart.emartindia.models;

import com.google.gson.annotations.SerializedName;

/*
 * @author Bipin Singh
 */

public class OrderItems {

    @SerializedName("product")
    String Product;

    @SerializedName("name")
    String Name;

    @SerializedName("qty")
    int Quantity;

    @SerializedName("image")
    String Image;

    @SerializedName("price")
    double Price;

    public OrderItems() {

    }

    public OrderItems(String product, String name, int quantity, String image, double price) {
        Product = product;
        Name = name;
        Quantity = quantity;
        Image = image;
        Price = price;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public String getProduct() {
        return Product;
    }

    public void setProduct(String product) {
        Product = product;
    }
}
