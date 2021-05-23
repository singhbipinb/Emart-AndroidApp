package com.emart.emartindia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

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

public class ProductDetail extends AppCompatActivity {

    ImageView imageView;
    TextView name,price,description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);


        imageView= findViewById(R.id.imageView);
        name = findViewById(R.id.textView);
        price = findViewById(R.id.textView2);
        description = findViewById(R.id.descriptionTV);


        Intent intent = getIntent();

        String productid = intent.getExtras().getString("productid");


        final ProductInterface apiser = apiClient.getClient().create(ProductInterface.class);

        Call<Products> call = apiser.GetProduct(productid);
        call.enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products> response) {

                Glide.with(getApplicationContext()).load(response.body().getImage()).into(imageView);

//                imageView.setImageURI(Uri.parse(response.body().getImage()));
                name.setText(response.body().getName());
                price.setText(response.body().getPrice());

                String[] descar = response.body().getDescription().split(",");

                String desc = "";
                for (String i : descar){
                    desc = desc + "- "+i+"\n";

                }

                description.setText(desc);


//
//
//                System.out.println("REvi "+response.body().getReviews()[0].getComment());

                List<String> list = new ArrayList<>();

                for (Reviews r : response.body().getReviews()){

                    String Ratingstar ="";
                    for (int i=0;i<r.getRating();i++){
                        Ratingstar = Ratingstar+"\u2605";
                    }

                    list.add(r.getReviewer()+"\n"+Ratingstar+"\n"+r.getComment());
                }
//                ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.reviewlayout,list);

                LinearLayout reviLi = findViewById(R.id.reviewline);

                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

                for (int i = 0; i < response.body().getReviews().length; i++) {
                    View view = inflater.inflate(R.layout.reviewlayout,null);

                    TextView tv1 = view.findViewById(R.id.textView6);

                    tv1.setText(""+response.body().getReviews()[i].getReviewer());


                    String Ratingstar ="";
                    for (int j=0;j<response.body().getReviews()[i].getRating();j++){
                        Ratingstar = Ratingstar+"\u2605";
                    }


                    TextView tv2 = view.findViewById(R.id.textView4);

                    tv2.setText(""+Ratingstar);
                    TextView tv3 = view.findViewById(R.id.textView5);

                    tv3.setText(""+response.body().getReviews()[i].getComment());

                    reviLi.addView(view);
                }

            }

            @Override
            public void onFailure(Call<Products> call, Throwable t) {

            }
        });

    }
}