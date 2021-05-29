package com.emart.emartindia.apiclient;

import com.emart.emartindia.models.Products;
import com.google.gson.JsonElement;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProductInterface {

//    public Products products = null;

    @GET("/api/products/{pid}")
    Call<Products> GetProduct(@Path("pid") String product);

    @PUT("/api/products/{pid}")
    Call<Products> UpdateProduct(@Path("pid") String product);


    @DELETE("/api/products/{pid}")
    Call<Products> DeleteProduct(@Path("pid") String product);

    @POST("/api/products/{pid}/reviews")
    Call<Products> AddReview(@Path("pid") String product);


    @GET("/api/products?page=2")
    Call<JsonElement> GetAllProduct();


    @POST("/api/products/")
    Call<List<Products>> AddProduct();

    @GET("api/browse/{category}")
    Call<JsonElement> browseProductbyCategory(@Path("category") String category);

//    @FormUrlEncoded
//    Observable<String> executeLogin(@Field("email") String email, @Field("password") String password);


}
