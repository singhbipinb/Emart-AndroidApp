package com.emart.emartindia;

import androidx.appcompat.app.AppCompatActivity;

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
import com.emart.emartindia.models.Products;
import com.emart.emartindia.models.Users;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckoutSteps extends BaseNavigation {

    SQLiteDatabase mydb;

    EditText shippingmob, shippingdd, shippingcity, shippingzip, shippingcountry;
    int  mobile, zip;
    String address, city, country;
    OrderDetail OrderSummary;
    LinearLayout checkoutadress, checkoutpayment, checkoutsummary;

    TextView name, email, address2, paymentmethod, itemtotal, shipping,tax, totalamount,orderidtv;

    JsonObject address1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.transition.fadein, R.transition.fadeout);
        LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.activity_checkout_steps,null,false);

        frameLayout.addView(view);

        shippingmob = findViewById(R.id.coaddmob);
        shippingdd = findViewById(R.id.coadd1);
        shippingcity = findViewById(R.id.coaddcity);
        shippingzip = findViewById(R.id.coaddzip);
        shippingcountry = findViewById(R.id.coaddCon);


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


        mydb = openOrCreateDatabase("cartdb",MODE_PRIVATE,null);

        mydb.execSQL("create table if not exists Address(mobile integer, address varchar, city varchar,postalCode integer, country varchar )");


    }

    public void movetopayment(View view) {

        mobile = Integer.parseInt(shippingmob.getText().toString().substring(1,shippingmob.getText().toString().length()-1));
        zip = Integer.parseInt(shippingzip.getText().toString().substring(1,shippingzip.getText().toString().length()-1));

        city = shippingcity.getText().toString();
        address = shippingdd.getText().toString();
        country = shippingcountry.getText().toString();

        mydb.execSQL("insert into Address values('"+mobile+"','"+address+"','"+city+"','"+zip+"','"+country+"')");

        String shippingadd = "{\"mobile\":"+mobile + ",\"address\":"+address +",\"city\":"+city
                +",\"postalCode\":"+zip +",\"country\":"+country+"}";

        address1 = new Gson().fromJson(shippingadd, JsonObject.class);

        System.out.println(address1);


//        OrderSummary.setShippingAddress(address1);

        checkoutadress.setVisibility(View.INVISIBLE);
        checkoutpayment.setVisibility(View.VISIBLE);
        checkoutsummary.setVisibility(View.INVISIBLE);

    }

    public void createOrder(View view) {


        SharedPreferences sh = getSharedPreferences("LoginData",MODE_PRIVATE);


        System.out.println(""+OrderSummary.toString());

        final OrderInterface apiser = apiClient.getClient().create(OrderInterface.class);

        Call<Orders> call = apiser.CreateOrder("Bearer "+sh.getString("authtoken",""),OrderSummary);

        call.enqueue(new Callback<Orders>() {
            @Override
            public void onResponse(Call<Orders> call, Response<Orders> response) {

                System.out.println("Order Created");

                System.out.println("resds" + response.body().toString());

                Intent intent = new Intent(getApplicationContext(),OrderDetails.class);
                intent.putExtra("orderid",response.body().getId());
                startActivity(intent);
                mydb.execSQL("DELETE FROM MyCart");
                mydb.execSQL("VACUUM");



            }

            @Override
            public void onFailure(Call<Orders> call, Throwable t) {
                System.out.println("Order fail \n"+t);

            }
        });




    }

    public void movetosummary(View view) {

        SharedPreferences sh = getSharedPreferences("LoginData",MODE_PRIVATE);

        Users user = new Users();
        user.setId(sh.getString("authid",""));

//        OrderSummary.setUser(user);
//
//        OrderSummary.setTaxPrice(0);
//        OrderSummary.setShippingPrice(0);

        double Totalprice=0;

        Cursor result = mydb.rawQuery("select * from MyCart",null);

        OrderItems[] products = new OrderItems[result.getCount()];

        int i=0;
        if(result.moveToFirst()){
            do{

                System.out.println(""+result.getString(0)+" "+result.getString(1)+" "+result.getString(2)+" "
                        +result.getDouble(3)+" "+result.getInt(4)+" "+result.getInt(5)+" ");

                products[i] = new OrderItems();
                products[i].setProduct(result.getString(0));
                products[i].setName(result.getString(1));
                products[i].setImage(result.getString(2));
                products[i].setPrice(result.getDouble(3));
                products[i].setQuantity(result.getInt(5));

                Totalprice = Totalprice+ (result.getDouble(3)*result.getInt(5));

                i++;
            }

            while (result.moveToNext());

        }

//        OrderSummary.setTotalPrice(Totalprice);
//        OrderSummary.setPaymentMethod("Cash on Delivery");

        OrderSummary = new OrderDetail(user,"Cash on Delivery",0,0,Totalprice,products,address1);

        name.setText(""+sh.getString("authname",""));
        email.setText(""+sh.getString("authemail",""));
        address2.setText(""+OrderSummary.getShippingAddress());
        itemtotal.setText(""+OrderSummary.getTotalPrice());
        shipping.setText(""+OrderSummary.getShippingPrice());
        tax.setText(""+OrderSummary.getTaxPrice());
        totalamount.setText(""+OrderSummary.getTotalPrice());
//        orderidtv.setText(""+OrderSummary.getId().toUpperCase());
        paymentmethod.setText(""+OrderSummary.getPaymentMethod());



        checkoutadress.setVisibility(View.INVISIBLE);
        checkoutpayment.setVisibility(View.INVISIBLE);
        checkoutsummary.setVisibility(View.VISIBLE);
    }
}