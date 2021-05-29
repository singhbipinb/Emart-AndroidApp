package com.emart.emartindia;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.emart.emartindia.apiclient.ProductInterface;
import com.emart.emartindia.apiclient.apiClient;
import com.emart.emartindia.models.Products;
import com.emart.emartindia.models.Reviews;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetail extends BaseNavigation {

    ImageView imageView;
    TextView name, price, description, noreview;
    String productid, AddQuery, ExecuteQuery;
    SQLiteDatabase mydb;
    Products products = new Products();

    ScrollView sc;
    ProgressBar loader;

    Spinner qty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Product Detail");
        overridePendingTransition(R.transition.fadein, R.transition.fadeout);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.activity_product_detail, null, false);

        frameLayout.addView(view);


        imageView = findViewById(R.id.imageView);
        name = findViewById(R.id.textView);
        price = findViewById(R.id.textView2);
        description = findViewById(R.id.descriptionTV);
        qty = findViewById(R.id.button3);
        noreview = findViewById(R.id.reviewno);
        loader = findViewById(R.id.loading);
        sc = findViewById(R.id.productdetailavil);

        mydb = openOrCreateDatabase("cartdb", MODE_PRIVATE, null);


        Intent intent = getIntent();

        productid = intent.getExtras().getString("productid");


        final ProductInterface apiser = apiClient.getClient().create(ProductInterface.class);

        Call<Products> call = apiser.GetProduct(productid);
        call.enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products> response) {

                products = response.body();

                Glide.with(getApplicationContext()).load(response.body().getImage()).into(imageView);

//                imageView.setImageURI(Uri.parse(response.body().getImage()));
                name.setText(response.body().getName());
                price.setText(response.body().getPrice());

                String[] descar = response.body().getDescription().split(",");

                String desc = "";
                for (String i : descar) {
                    desc = desc + "- " + i + "\n";

                }

                description.setText(desc);

                List<Integer> stock = new ArrayList<>();
                for (int i = 1; i <= Integer.parseInt(String.valueOf(response.body().getCountInStock())) / 10; i++) {
                    stock.add(i);
                }

                ArrayAdapter<Integer> qtyAdapter = new ArrayAdapter<Integer>(getApplicationContext(), android.R.layout.simple_spinner_item, stock);

                qtyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                qty.setAdapter(qtyAdapter);

                qty.setSelection(0);


                List<String> list = new ArrayList<>();

                for (Reviews r : response.body().getReviews()) {

                    String Ratingstar = "";
                    for (int i = 0; i < r.getRating(); i++) {
                        Ratingstar = Ratingstar + "\u2605";
                    }

                    list.add(r.getReviewer() + "\n" + Ratingstar + "\n" + r.getComment());
                }
//                ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.reviewlayout,list);

                LinearLayout reviLi = findViewById(R.id.reviewline);

                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

                if (response.body().getReviews().length == 0) {
                    noreview.setVisibility(View.VISIBLE);
                }

                for (int i = 0; i < response.body().getReviews().length; i++) {
                    View view = inflater.inflate(R.layout.reviewlayout, null);

                    TextView tv1 = view.findViewById(R.id.textView6);

                    tv1.setText("" + response.body().getReviews()[i].getReviewer());


                    String Ratingstar = "";
                    for (int j = 0; j < response.body().getReviews()[i].getRating(); j++) {
                        Ratingstar = Ratingstar + "\u2605";
                    }


                    TextView tv2 = view.findViewById(R.id.textView4);

                    tv2.setText("" + Ratingstar);
                    TextView tv3 = view.findViewById(R.id.textView5);

                    tv3.setText("" + response.body().getReviews()[i].getComment());

                    reviLi.addView(view);
                }

                loader.setVisibility(View.INVISIBLE);
                sc.setVisibility(View.VISIBLE);


            }

            @Override
            public void onFailure(Call<Products> call, Throwable t) {

            }
        });

    }

    public void AddtoCart(View view) {


        AddQuery = "insert into MyCart values('" + productid + "','" + products.getName() + "','" +
                products.getImage() + "'," + products.getPrice() + "," +
                products.getCountInStock() + "," + Integer.parseInt(String.valueOf(qty.getSelectedItem())) + ")";


        mydb.execSQL("create table if not exists MyCart(itemid varchar primary key,name varchar, image varchar, price double," +
                "countinstock integer, qty integer)");

        Cursor resultset = mydb.rawQuery("select * from MyCart where itemid='" + productid + "'", null);

        if (resultset.moveToFirst()) {
            int prevqty = resultset.getInt(5) + Integer.parseInt(String.valueOf(qty.getSelectedItem()));

            String updatequery = "update MyCart set qty=" + prevqty + " where itemid='" + productid + "'";

            mydb.execSQL(updatequery);
            Toast.makeText(getApplicationContext(), "Product Added to Cart", Toast.LENGTH_LONG).show();

        } else {

            mydb.execSQL(AddQuery);
            Toast.makeText(getApplicationContext(), "Product Added to Cart", Toast.LENGTH_LONG).show();


        }


    }
}