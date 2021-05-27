package com.emart.emartindia.apiclient;

import com.emart.emartindia.models.OrderDetail;
import com.emart.emartindia.models.Orders;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

//import java.util.Observable;

public interface OrderInterface {

//    public Products products = null;

    @GET("/api/orders/{oid}")
    Call<OrderDetail> GetOne (@Path("oid") String product, @Header("Authorization") String authHeader);

    @GET("/api/orders/")
    Call<List<Orders>> GetAll (@Header("Authorization") String authHeader);

    @GET("/api/orders/myorders")
    Call<List<Orders>> GetUsersAll (@Header("Authorization") String authHeader);

    @PUT("/api/orders/{oid}/pay")
    Call<Orders> PayOrder (@Path("oid") String orderid);

    @PUT("/api/orders/{oid}/deliver")
    Call<Orders> DeliverOrder (@Path("oid") String orderid);

    @POST("/api/orders/")
    Call<Orders> CreateOrder (@Header("Authorization") String authHeader, @Body OrderDetail order );




//    @FormUrlEncoded
//    Observable<String> executeLogin(@Field("email") String email, @Field("password") String password);


}
