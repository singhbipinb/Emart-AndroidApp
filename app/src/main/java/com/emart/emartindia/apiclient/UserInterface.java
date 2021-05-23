package com.emart.emartindia.apiclient;

import com.emart.emartindia.models.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

//import java.util.Observable;

public interface UserInterface {

//    public Products products = null;

    @GET("/api/users/{uid}")

Call<Users> GetOneUser (@Path("pid") String user);

    @GET("/api/users/")
    Call<List<Users>> GetAllUser ();


    @POST("/api/users/")
    Call<Users> CreateUser (@Body Users user);

    @PUT("/api/users/{uid}")

    Call<Users> UpdateUser (@Path("pid") String user);

    @DELETE("/api/users/{uid}")

    Call<Users> DeleteUser (@Path("pid") String user);

    @POST("/api/users/login")
    Call<Users> AuthUser (@Body Users user);

    @GET("/api/users/profile")
    Call<Users> getUserProfile();


//    @FormUrlEncoded
//    Observable<String> executeLogin(@Field("email") String email, @Field("password") String password);


}
