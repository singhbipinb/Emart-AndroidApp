package com.emart.emartindia.models;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;


public class Orders {


    @SerializedName("_id")
    private String id;

    @SerializedName("user")
    private String User;

    @SerializedName("paymentMethod")
    private String paymentMethod;

    @SerializedName("taxPrice")
    private String taxPrice;

    @SerializedName("shippingPrice")
    private String shippingPrice;

    @SerializedName("totalPrice")
    private String totalPrice;

    @SerializedName("isPaid")
    private String isPaid;

    @SerializedName("paidAt")
    private String paidAt;

    @SerializedName("isDelivered")
    private boolean isDelivered;

    @SerializedName("deliveredAt")
    private String deliveredAt;

    @SerializedName("orderItems")
    private OrderItems[] orderItems;

    @SerializedName("shippingAddress")
    private JsonObject shippingAddress;

    @SerializedName("createdAt")

    private String CreatedAt;

    public Orders(String id, String user, String paymentMethod, String taxPrice, String shippingPrice, String totalPrice, String isPaid, String paidAt, boolean isDelivered, String deliveredAt, OrderItems[] orderItems, JsonObject shippingAddress, String createdAt) {
        this.id = id;
        User = user;
        this.paymentMethod = paymentMethod;
        this.taxPrice = taxPrice;
        this.shippingPrice = shippingPrice;
        this.totalPrice = totalPrice;
        this.isPaid = isPaid;
        this.paidAt = paidAt;
        this.isDelivered = isDelivered;
        this.deliveredAt = deliveredAt;
        this.orderItems = orderItems;
        this.shippingAddress = shippingAddress;
        CreatedAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getTaxPrice() {
        return taxPrice;
    }

    public void setTaxPrice(String taxPrice) {
        this.taxPrice = taxPrice;
    }

    public String getShippingPrice() {
        return shippingPrice;
    }

    public void setShippingPrice(String shippingPrice) {
        this.shippingPrice = shippingPrice;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(String isPaid) {
        this.isPaid = isPaid;
    }

    public String getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(String paidAt) {
        this.paidAt = paidAt;
    }

    public boolean getIsDelivered() {
        return isDelivered;
    }

    public void setIsDelivered(boolean isDelivered) {
        this.isDelivered = isDelivered;
    }

    public String getDeliveredAt() {
        return deliveredAt;
    }

    public void setDeliveredAt(String deliveredAt) {
        this.deliveredAt = deliveredAt;
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

    public String getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        CreatedAt = createdAt;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id='" + id + '\'' +
                ", User='" + User + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", taxPrice='" + taxPrice + '\'' +
                ", shippingPrice='" + shippingPrice + '\'' +
                ", totalPrice='" + totalPrice + '\'' +
                ", isPaid='" + isPaid + '\'' +
                ", paidAt='" + paidAt + '\'' +
                ", isDelivered=" + isDelivered +
                ", deliveredAt='" + deliveredAt + '\'' +
                ", orderItems=" + Arrays.toString(orderItems) +
                ", shippingAddress=" + shippingAddress +
                ", CreatedAt='" + CreatedAt + '\'' +
                '}';
    }
}
