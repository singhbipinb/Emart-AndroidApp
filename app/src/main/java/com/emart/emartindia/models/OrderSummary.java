package com.emart.emartindia.models;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

/*
 * @author Bipin Singh
 */

public class OrderSummary {

    @SerializedName("user")
    private Users User;

    @SerializedName("paymentMethod")
    private String paymentMethod;

    @SerializedName("taxPrice")
    private double taxPrice;

    @SerializedName("shippingPrice")
    private double shippingPrice;

    @SerializedName("totalPrice")
    private double totalPrice;

    @SerializedName("orderItems")
    private OrderItems[] orderItems;

    @SerializedName("shippingAddress")
    private JsonObject shippingAddress;


    public OrderSummary(Users user, String paymentMethod, double taxPrice, double shippingPrice, double totalPrice, OrderItems[] orderItems, JsonObject shippingAddress) {
        User = user;
        this.paymentMethod = paymentMethod;
        this.taxPrice = taxPrice;
        this.shippingPrice = shippingPrice;
        this.totalPrice = totalPrice;
        this.orderItems = orderItems;
        this.shippingAddress = shippingAddress;
    }


    public Users getUser() {
        return User;
    }

    public void setUser(Users user) {
        User = user;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getTaxPrice() {
        return taxPrice;
    }

    public void setTaxPrice(double taxPrice) {
        this.taxPrice = taxPrice;
    }

    public double getShippingPrice() {
        return shippingPrice;
    }

    public void setShippingPrice(double shippingPrice) {
        this.shippingPrice = shippingPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderItems[] getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(OrderItems[] orderItems) {
        this.orderItems = orderItems;
    }

    public JsonObject getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(JsonObject shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}
