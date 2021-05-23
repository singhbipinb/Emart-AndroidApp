package com.emart.emartindia.models;

import com.google.gson.annotations.SerializedName;

public class OrderItems {

    @SerializedName("name")
    String Name;

    @SerializedName("qty")
    String Quantity;

    @SerializedName("image")
    String Image;

    @SerializedName("price")
    String Price;

    public OrderItems(String name, String quantity, String image, String price) {
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

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
