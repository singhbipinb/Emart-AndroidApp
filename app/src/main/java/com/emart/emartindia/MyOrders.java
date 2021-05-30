package com.emart.emartindia;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.emart.emartindia.adapter.OrdersAdapter;
import com.emart.emartindia.apiclient.OrderInterface;
import com.emart.emartindia.apiclient.apiClient;
import com.emart.emartindia.models.Orders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * @author Bipin Singh
 */

public class MyOrders extends BaseNavigation {


    LinearLayout linearLayout;
    ProgressBar loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("My Orders");
        overridePendingTransition(R.transition.fadein, R.transition.fadeout);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.activity_my_orders, null, false);

        frameLayout.addView(view);


        linearLayout = findViewById(R.id.myordersnoorder);
        loader = findViewById(R.id.loading);

        getOrders();


    }

    public void getOrders() {

        final OrderInterface apiser = apiClient.getClient().create(OrderInterface.class);

        SharedPreferences sh = getSharedPreferences("LoginData", MODE_PRIVATE);


        String token = sh.getString("authtoken", "");


        Call<List<Orders>> call = apiser.GetUsersAll("Bearer " + token);
        call.enqueue(new Callback<List<Orders>>() {
            @Override
            public void onResponse(Call<List<Orders>> call, Response<List<Orders>> response) {


                ArrayList<Orders> list = new ArrayList<>(response.body());
                Collections.reverse(list);


                if (list.isEmpty()) {

                    loader.setVisibility(View.INVISIBLE);
                    linearLayout.setVisibility(View.VISIBLE);

                } else {
                    loader.setVisibility(View.INVISIBLE);
                    RecyclerView recyclerView = findViewById(R.id.ordersrecyclerview);
                    recyclerView.setVisibility(View.VISIBLE);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setAdapter(new OrdersAdapter(list));

                }

            }

            @Override
            public void onFailure(Call<List<Orders>> call, Throwable t) {


            }
        });


    }


    public void StartSHopping(View view) {

        Intent intent = new Intent(this, CheckoutSteps.class);
        startActivity(intent);

    }
}