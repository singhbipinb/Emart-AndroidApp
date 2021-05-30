package com.emart.emartindia;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.emart.emartindia.apiclient.OrderInterface;
import com.emart.emartindia.apiclient.apiClient;
import com.emart.emartindia.models.OrderDetail;
import com.emart.emartindia.models.OrderItems;
import com.emart.emartindia.models.Orders;
import com.emart.emartindia.models.Users;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * @author Bipin Singh
 */

public class CheckoutSteps extends BaseNavigation {

    SQLiteDatabase mydb;

    EditText shippingmob, shippingdd, shippingcity, shippingzip, shippingcountry;
    int mobile, zip;
    String address, city, country;
    OrderDetail OrderSummary;
    LinearLayout checkoutadress, checkoutpayment, checkoutsummary;

    TextView name, email, address2, paymentmethod, itemtotal, shipping, tax, totalamount, orderidtv, contact, adderror;

    JsonObject address1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Checkout");
        overridePendingTransition(R.transition.fadein, R.transition.fadeout);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.activity_checkout_steps, null, false);

        frameLayout.addView(view);

        shippingmob = findViewById(R.id.coaddmob);
        shippingdd = findViewById(R.id.coadd1);
        shippingcity = findViewById(R.id.coaddcity);
        shippingzip = findViewById(R.id.coaddzip);
        shippingcountry = findViewById(R.id.coaddCon);
        contact = findViewById(R.id.shippingmobile);
        adderror = findViewById(R.id.addresserror);

        name = findViewById(R.id.shippingname);
        email = findViewById(R.id.shippingemail);
        address2 = findViewById(R.id.shippingaddress);
        paymentmethod = findViewById(R.id.odpaymentmethod);
        itemtotal = findViewById(R.id.oditemprice);
        shipping = findViewById(R.id.odshipping);
        tax = findViewById(R.id.odtaxes);
        totalamount = findViewById(R.id.odtotalamount);
        orderidtv = findViewById(R.id.odorderid);

        checkoutadress = findViewById(R.id.checkoutaddress);
        checkoutpayment = findViewById(R.id.checkoutpayment);
        checkoutsummary = findViewById(R.id.checkoutoverview);


//        OrderSummary = new OrderDetail();


        mydb = openOrCreateDatabase("cartdb", MODE_PRIVATE, null);

        mydb.execSQL("create table if not exists Address(mobile integer, address varchar, city varchar,postalCode integer, country varchar )");


        Cursor result = mydb.rawQuery("select * from Address", null);

        if (result.moveToLast()) {
            shippingmob.setText("" + result.getInt(0));
            shippingdd.setText("" + result.getString(1));
            shippingcity.setText("" + result.getString(2));
            shippingzip.setText("" + result.getInt(3));
            shippingcountry.setText("" + result.getString(4));
        }


    }

    public void movetopayment(View view) {


        if (shippingcountry.getText().length() == 0 || shippingzip.getText().length() == 0 || shippingcity.getText().length() == 0 ||
                shippingdd.getText().length() == 0 || shippingmob.getText().length() == 0) {
            adderror.setText("Please enter your complete address");
        } else {


            mobile = Integer.parseInt(String.valueOf(shippingmob.getText()));
//        mobile = Integer.parseInt(shippingmob.getText().toString().substring(1,shippingmob.getText().toString().length()-1));
            zip = Integer.parseInt(String.valueOf(shippingzip.getText()));

            city = shippingcity.getText().toString();
            address = shippingdd.getText().toString();
            country = shippingcountry.getText().toString();

            mydb.execSQL("insert into Address values('" + mobile + "','" + address + "','" + city + "','" + zip + "','" + country + "')");

            String shippingadd = "{\"mobile\":" + mobile + ",\"address\":" + address + ",\"city\":" + city
                    + ",\"postalCode\":" + zip + ",\"country\":" + country + "}";

            address1 = new Gson().fromJson(shippingadd, JsonObject.class);


            checkoutadress.setVisibility(View.INVISIBLE);
            checkoutpayment.setVisibility(View.VISIBLE);
            checkoutsummary.setVisibility(View.INVISIBLE);


        }
    }

    public void createOrder(View view) {


        SharedPreferences sh = getSharedPreferences("LoginData", MODE_PRIVATE);


        final OrderInterface apiser = apiClient.getClient().create(OrderInterface.class);

        Call<Orders> call = apiser.CreateOrder("Bearer " + sh.getString("authtoken", ""), OrderSummary);

        call.enqueue(new Callback<Orders>() {
            @Override
            public void onResponse(Call<Orders> call, Response<Orders> response) {


                Intent intent = new Intent(getApplicationContext(), OrderDetails.class);
                intent.putExtra("orderid", response.body().getId());
                startActivity(intent);

                mydb.execSQL("DELETE FROM MyCart");
                mydb.execSQL("VACUUM");
                finish();


            }

            @Override
            public void onFailure(Call<Orders> call, Throwable t) {


            }
        });


    }

    public void movetosummary(View view) {

        SharedPreferences sh = getSharedPreferences("LoginData", MODE_PRIVATE);

        Users user = new Users();
        user.setId(sh.getString("authid", ""));


        double Totalprice = 0;

        Cursor result = mydb.rawQuery("select * from MyCart", null);

        OrderItems[] products = new OrderItems[result.getCount()];

        int i = 0;
        if (result.moveToFirst()) {
            do {


                products[i] = new OrderItems();
                products[i].setProduct(result.getString(0));
                products[i].setName(result.getString(1));
                products[i].setImage(result.getString(2));
                products[i].setPrice(result.getDouble(3));
                products[i].setQuantity(result.getInt(5));

                Totalprice = Totalprice + (result.getDouble(3) * result.getInt(5));

                i++;
            }

            while (result.moveToNext());

        }


        OrderSummary = new OrderDetail(user, "Cash on Delivery", 0, 0, Totalprice, products, address1);

        name.setText("Name: " + sh.getString("authname", ""));
        email.setText("Email: " + sh.getString("authemail", ""));
        contact.setText("Contact" + OrderSummary.getShippingAddress().get("mobile"));
        address2.setText("Address: " + OrderSummary.getShippingAddress().get("address") + " " + OrderSummary.getShippingAddress().get("city")
                + " " + OrderSummary.getShippingAddress().get("country") + " " + OrderSummary.getShippingAddress().get("postalCode"));
        itemtotal.setText("" + OrderSummary.getTotalPrice());
        shipping.setText("" + OrderSummary.getShippingPrice());
        tax.setText("" + OrderSummary.getTaxPrice());
        totalamount.setText("" + OrderSummary.getTotalPrice());
        paymentmethod.setText("" + OrderSummary.getPaymentMethod());


        checkoutadress.setVisibility(View.INVISIBLE);
        checkoutpayment.setVisibility(View.INVISIBLE);
        checkoutsummary.setVisibility(View.VISIBLE);
    }
}