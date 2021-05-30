package com.emart.emartindia;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.emart.emartindia.apiclient.OrderInterface;
import com.emart.emartindia.apiclient.apiClient;
import com.emart.emartindia.models.OrderDetail;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * @author Bipin Singh
 */

public class OrderDetails extends BaseNavigation {

    String orderid;

    TextView name, email, address, paymentmethod, itemtotal, shipping, tax, totalamount, orderidtv, mobile;

    ProgressBar loader;

    ScrollView sc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Order Detail");
        overridePendingTransition(R.transition.fadein, R.transition.fadeout);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.activity_order_details, null, false);

        frameLayout.addView(view);


        name = findViewById(R.id.shippingname);
        email = findViewById(R.id.shippingemail);
        address = findViewById(R.id.shippingaddress);
        paymentmethod = findViewById(R.id.odpaymentmethod);
        itemtotal = findViewById(R.id.oditemprice);
        shipping = findViewById(R.id.odshipping);
        tax = findViewById(R.id.odtaxes);
        totalamount = findViewById(R.id.odtotalamount);
        orderidtv = findViewById(R.id.odorderid);
        mobile = findViewById(R.id.shippingmobile);

        loader = findViewById(R.id.loading);
        sc = findViewById(R.id.orderdetailspresent);


        Intent intent = getIntent();

        orderid = intent.getStringExtra("orderid");


        showOrderDetails();

    }


    public void showOrderDetails() {


        final OrderInterface apiser = apiClient.getClient().create(OrderInterface.class);

        SharedPreferences sh = getSharedPreferences("LoginData", MODE_PRIVATE);


        String token = sh.getString("authtoken", "");


        Call<OrderDetail> call = apiser.GetOne(orderid, "Bearer " + token);
        call.enqueue(new Callback<OrderDetail>() {
            @Override
            public void onResponse(Call<OrderDetail> call, Response<OrderDetail> response) {


                OrderDetail order = response.body();

                name.setText("Name: " + order.getUser().getName());
                mobile.setText("Contact: " + order.getShippingAddress().get("mobile"));
                email.setText("Email: " + order.getUser().getEmail());
                address.setText("Address: " + order.getShippingAddress().get("address") + " " + order.getShippingAddress().get("city")
                        + " " + order.getShippingAddress().get("country") + " " + order.getShippingAddress().get("postalCode"));
                itemtotal.setText("" + order.getTotalPrice());
                shipping.setText("" + order.getShippingPrice());
                tax.setText("" + order.getTaxPrice());
                totalamount.setText("" + order.getTotalPrice());
                orderidtv.setText("Order ID: " + order.getId().toUpperCase());
                paymentmethod.setText("" + order.getPaymentMethod());


                LinearLayout reviLi = findViewById(R.id.oilLinearlayout);

                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

                for (int i = 0; i < response.body().getOrderItems().length; i++) {
                    View view = inflater.inflate(R.layout.orderitemlist, null);

                    ImageView img = view.findViewById(R.id.oilimg);

                    Glide.with(getApplicationContext()).load(response.body().getOrderItems()[i].getImage()).into(img);


                    TextView tv1 = view.findViewById(R.id.oilname);

                    tv1.setText("" + response.body().getOrderItems()[i].getName());


                    TextView tv2 = view.findViewById(R.id.oilprice);

                    tv2.setText("\u20B9 " + response.body().getOrderItems()[i].getPrice());
                    TextView tv3 = view.findViewById(R.id.oilqty);

                    tv3.setText("Quantity: " + response.body().getOrderItems()[i].getQuantity());

                    reviLi.addView(view);
                }

                loader.setVisibility(View.INVISIBLE);
                sc.setVisibility(View.VISIBLE);


            }

            @Override
            public void onFailure(Call<OrderDetail> call, Throwable t) {


            }
        });


    }


}