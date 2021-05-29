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

import androidx.appcompat.app.AppCompatDelegate;

import com.bumptech.glide.Glide;
import com.emart.emartindia.apiclient.ProductInterface;
import com.emart.emartindia.apiclient.apiClient;
import com.google.gson.Gson;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import retrofit2.Retrofit;

public class MainActivity extends BaseNavigation {

    Gson gson = new Gson();
    EditText email, pass;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageViewProduct1;
    ImageView imageViewProduct2;
    ImageView imageViewProduct3;
    ImageView imageViewProduct4;
    SharedPreferences LoginToken;
    private Retrofit retrofit;
    private ProductInterface productInterface;
    //    private String BASE_URL = "http://10.0.2.2:5000";
    private apiClient apiClient;
    private int CarouselImages[] = new int[]{
            R.drawable.slide1, R.drawable.slide2, R.drawable.slide3, R.drawable.slide4
    };
    private String[] CauroselImagesTitle = new String[]{
            "Phone", "Laptops", "Home Appliances", "Gadgets"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.transition.fadein, R.transition.fadeout);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.activity_main, null, false);

        frameLayout.addView(view);


        SQLiteDatabase mydb = openOrCreateDatabase("cartdb", MODE_PRIVATE, null);
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
        Glide.with(this).load("https://emibaba.com/wp-content/uploads/2021/01/apple-iwatch-series-6-blue-5.jpg").into(imageViewProduct3);

        imageViewProduct4 = findViewById(R.id.Product4);
        Glide.with(this).load("https://i.gadgets360cdn.com/products/large/mi-notebook-14-horizon-edition-1700x800-1591862267.jpg").into(imageViewProduct4);


    }

    @Override
    protected void onResume() {
        super.onResume();
        loginStatus();

    }

    public void homeproduct1(View view) {

        Intent intent = new Intent(this, ProductDetail.class);
        intent.putExtra("productid", "607718CE357D423F9C1D3189");
        startActivity(intent);

    }

    public void homeproduct2(View view) {
        Intent intent = new Intent(this, ProductDetail.class);
        intent.putExtra("productid", "6075D8FAFCB3C33754DD15E5");
        startActivity(intent);
    }

    public void homeproduct3(View view) {
        Intent intent = new Intent(this, ProductDetail.class);
        intent.putExtra("productid", "60B20BBA3D81572F29F6F9CE");
        startActivity(intent);
    }

    public void homeproduct4(View view) {
        Intent intent = new Intent(this, ProductDetail.class);
        intent.putExtra("productid", "607A9F88668A0742B4306B6B");
        startActivity(intent);
    }
}