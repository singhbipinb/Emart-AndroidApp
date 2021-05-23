package com.emart.emartindia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.emart.emartindia.adapter.OrdersAdapter;
import com.emart.emartindia.adapter.ProductsAdapter;
import com.emart.emartindia.apiclient.OrderInterface;
import com.emart.emartindia.apiclient.apiClient;
import com.emart.emartindia.models.Orders;
import com.emart.emartindia.models.Products;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyOrders extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);



getOrders();



    }

    public void getOrders(){

        final OrderInterface apiser = apiClient.getClient().create(OrderInterface.class);

        SharedPreferences sh = getSharedPreferences("LoginData",MODE_PRIVATE);



        String token =  sh.getString("authtoken","");

        System.out.println("Token "+token);

        Call<List<Orders>> call = apiser.GetUsersAll("Bearer "+"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjYwNzM0N2MzMjRjZWM4NWI5YzhjYzhiYyIsImlhdCI6MTYyMTc5MDM4NiwiZXhwIjoxNjI0MzgyMzg2fQ.gi5E-RpIpLSdQWJ8W-rxJ9m2DsC7F3KGRB5eLL72kYE");
        call.enqueue(new Callback<List<Orders>>() {
            @Override
            public void onResponse(Call<List<Orders>> call, Response<List<Orders>> response) {



                ArrayList<Orders> list = new ArrayList<>(response.body());

                System.out.println("Get State"+response.body().toString());

                RecyclerView recyclerView = findViewById(R.id.ordersrecyclerview);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(new OrdersAdapter(list));



            }

            @Override
            public void onFailure(Call<List<Orders>> call, Throwable t) {
                System.out.println("Nhi ho rha");
                System.out.println(""+t);


            }
        });



    }


}