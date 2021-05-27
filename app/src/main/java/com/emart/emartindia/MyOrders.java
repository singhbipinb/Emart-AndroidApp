package com.emart.emartindia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

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
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyOrders extends BaseNavigation {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.transition.fadein, R.transition.fadeout);
        LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.activity_my_orders,null,false);

        frameLayout.addView(view);



getOrders();



    }

    public void getOrders(){

        final OrderInterface apiser = apiClient.getClient().create(OrderInterface.class);

        SharedPreferences sh = getSharedPreferences("LoginData",MODE_PRIVATE);



        String token =  sh.getString("authtoken","");

        System.out.println("Token "+token);

        Call<List<Orders>> call = apiser.GetUsersAll("Bearer "+token);
        call.enqueue(new Callback<List<Orders>>() {
            @Override
            public void onResponse(Call<List<Orders>> call, Response<List<Orders>> response) {



                ArrayList<Orders> list = new ArrayList<>(response.body());
                Collections.reverse(list);

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