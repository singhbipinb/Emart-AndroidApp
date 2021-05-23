package com.emart.emartindia.models;

import com.google.gson.annotations.SerializedName;

public class StringResponse {

    @SerializedName("products")
    Object Products[];

    public Object[] getProducts() {
        return Products;
    }

    public void setProducts(Object products[]) {
        Products = products;
    }
}
