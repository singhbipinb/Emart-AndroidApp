package com.emart.emartindia;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.emart.emartindia.apiclient.OrderInterface;
import com.emart.emartindia.apiclient.ProductInterface;
import com.emart.emartindia.apiclient.UserInterface;
import com.emart.emartindia.apiclient.apiClient;
import com.emart.emartindia.models.Orders;
import com.emart.emartindia.models.Products;
import com.emart.emartindia.models.Users;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends BaseNavigation {

    private Retrofit retrofit;
    Gson gson = new Gson();
    private ProductInterface productInterface;
//    private String BASE_URL = "http://10.0.2.2:5000";
    private apiClient apiClient;
    EditText email,pass;

    SharedPreferences LoginToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.transition.fadein, R.transition.fadeout);
        LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.activity_main,null,false);

        frameLayout.addView(view);

        email = findViewById(R.id.editTextTextEmailAddress);
        pass = findViewById(R.id.editTextTextPassword);


//    loginStatus();


    }






    public void showData(){


        final OrderInterface apiser = apiClient.getClient().create(OrderInterface.class);

        SharedPreferences sh = getSharedPreferences("LoginData",MODE_PRIVATE);



       String token =  sh.getString("authtoken","");

        System.out.println("Token "+token);

        Call<List<Orders>> call = apiser.GetAll("Bearer "+"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjYwNzM0N2MzMjRjZWM4NWI5YzhjYzhiYyIsImlhdCI6MTYyMTc5MDM4NiwiZXhwIjoxNjI0MzgyMzg2fQ.gi5E-RpIpLSdQWJ8W-rxJ9m2DsC7F3KGRB5eLL72kYE");
        call.enqueue(new Callback<List<Orders>>() {
            @Override
            public void onResponse(Call<List<Orders>> call, Response<List<Orders>> response) {

                System.out.println("res code()()()"+response.code());

            }

            @Override
            public void onFailure(Call<List<Orders>> call, Throwable t) {
                System.out.println("Nhi ho rha");
                System.out.println(""+t);


            }
        });

//
//
//        Users user = new Users();
//
//        user.setEmail(email.getText().toString());
//        user.setPassword(pass.getText().toString());
//
//        Call<Users> call = apiser.AuthUser(user);
//
//        call.enqueue(new Callback<Users>() {
//            @Override
//            public void onResponse(Call<Users> call, Response<Users> response) {
//
//                System.out.println("Res: " +response.body().getName());
//
//            }
//
//            @Override
//            public void onFailure(Call<Users> call, Throwable t) {
//
//            }
//        });

    }

    public void browseCat(View view) {



//        Login();


//showData();

        Intent intent = new Intent(this, Cart.class);
        startActivity(intent);


    }
}