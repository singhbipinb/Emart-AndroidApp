package com.emart.emartindia.models;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;


public class OrderDetail {


    @SerializedName("_id")
    private String id;

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

    @SerializedName("isPaid")
    private boolean isPaid;

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

    public OrderDetail() {

    }

    public OrderDetail(Users user, String paymentMethod, double taxPrice, double shippingPrice, double totalPrice, OrderItems[] orderItems, JsonObject shippingAddress) {
        User = user;
        this.paymentMethod = paymentMethod;
        this.taxPrice = taxPrice;
        this.shippingPrice = shippingPrice;
        this.totalPrice = totalPrice;
        this.orderItems = orderItems;
        this.shippingAddress = shippingAddress;
    }

    public OrderDetail(String id, Users user, String paymentMethod, double taxPrice, double shippingPrice, double totalPrice, boolean isPaid, String paidAt, boolean isDelivered, String deliveredAt, OrderItems[] orderItems, JsonObject shippingAddress, String createdAt) {
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

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public String getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(String paidAt) {
        this.paidAt = paidAt;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public void setDelivered(boolean delivered) {
        isDelivered = delivered;
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
        return "OrderDetail{" +
                "id='" + id + '\'' +
                ", User=" + User +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", taxPrice=" + taxPrice +
                ", shippingPrice=" + shippingPrice +
                ", totalPrice=" + totalPrice +
                ", isPaid=" + isPaid +
                ", paidAt='" + paidAt + '\'' +
                ", isDelivered=" + isDelivered +
                ", deliveredAt='" + deliveredAt + '\'' +
                ", orderItems=" + Arrays.toString(orderItems) +
                ", shippingAddress=" + shippingAddress +
                ", CreatedAt='" + CreatedAt + '\'' +
                '}';
    }
}
