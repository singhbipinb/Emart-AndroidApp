package com.emart.emartindia;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
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
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

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

    private int CarouselImages[] = new int[] {
            R.drawable.slide1, R.drawable.slide2, R.drawable.slide3, R.drawable.slide4
    };

    private String[] CauroselImagesTitle = new String[] {
            "Phone", "Laptops", "Home Appliances", "Gadgets"
    };

    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageViewProduct1;
    ImageView imageViewProduct2;
    ImageView imageViewProduct3;
    ImageView imageViewProduct4;

    SharedPreferences LoginToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.transition.fadein, R.transition.fadeout);
        LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.activity_main,null,false);

        frameLayout.addView(view);



        SQLiteDatabase mydb = openOrCreateDatabase("cartdb",MODE_PRIVATE,null);
                mydb.execSQL("create table if not exists MyCart(itemid varchar primary key,name varchar, image varchar, price double," +
                        "countinstock integer, qty integer)");


//    loginStatus();

        CarouselView CarouselView = findViewById(R.id.Carousel);
        CarouselView.setPageCount(CarouselImages.length);
        CarouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(CarouselImages[position]);
            }
        });
        CarouselView.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(MainActivity.this, CauroselImagesTitle[position], Toast.LENGTH_SHORT).show();
            }
        });

        //Image URL
        imageView1 = findViewById(R.id.imgdelivery);
        Glide.with(this).load("https://www.merseysidesheds.co.uk/wp-content/uploads/2019/11/SHED-ICONS4.png").into(imageView1);

        imageView2 = findViewById(R.id.imgReturnPolicy);
        Glide.with(this).load("https://climatecase.com/wp-content/uploads/2018/06/Return-Policy_BLUE.png").into(imageView2);

        imageView3 = findViewById(R.id.img247);
        Glide.with(this).load("https://www.merseysidesheds.co.uk/wp-content/uploads/2019/11/SHED-ICONS6.png").into(imageView3);

        imageView4 = findViewById(R.id.imgSecPay);
        Glide.with(this).load("https://eazybillpay.files.wordpress.com/2015/08/payment-305.png").into(imageView4);

        imageViewProduct1 = findViewById(R.id.Product1);
        Glide.with(this).load("https://cdn.tmobile.com/content/dam/t-mobile/en-p/cell-phones/apple/Apple-iPhone-12-mini/Blue/Apple-iPhone-12-mini-Blue-frontimage.png").into(imageViewProduct1);

        imageViewProduct2 = findViewById(R.id.Product2);
        Glide.with(this).load("https://techsathi.com/wp-content/uploads/2019/06/Canon-EOS-800D-Price-in-Nepal-943x1024.jpg").into(imageViewProduct2);

        imageViewProduct3 = findViewById(R.id.Product3);
        Glide.with(this).load("https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/MJK63_VW_PF+watch-44-alum-silver-nc-se_VW_PF_WF_CO?wid=1400&hei=1400&trim=1,0&fmt=p-jpg&qlt=80&.v=1617229248000,1617299212000").into(imageViewProduct3);

        imageViewProduct4 = findViewById(R.id.Product4);
        Glide.with(this).load("https://www.htv1.eu/wp-content/uploads/2020/06/54.png").into(imageViewProduct4);


    }

    @Override
    protected void onResume() {
        super.onResume();
        loginStatus();

    }
}