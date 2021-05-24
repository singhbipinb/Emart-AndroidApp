package com.emart.emartindia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.emart.emartindia.adapter.ProductsAdapter;
import com.emart.emartindia.apiclient.ProductInterface;
import com.emart.emartindia.apiclient.apiClient;
import com.emart.emartindia.models.Products;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Browse extends BaseNavigation {

    ArrayList<Products> newlist;
    Gson gson = new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.activity_browse,null,false);

        frameLayout.addView(view);


        final ProductInterface apiser = apiClient.getClient().create(ProductInterface.class);

        List<Object> list = new ArrayList<>();


        Call<JsonElement> call = apiser.browseProductbyCategory("Smartphones");
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {

//                System.out.println(response.body() != null ? response.body().get("products") : null);

                if(response.body()!=null){
                    try {
                        System.out.println("resdghdf"+response.body());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                JSONObject object = null;
                try {
                    object = new JSONObject(response.body().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Type userListType = new TypeToken<ArrayList<Products>>(){}.getType();

                ArrayList<Products> userArray = null;
                try {
                    userArray = gson.fromJson(object.get("products").toString(), userListType);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                System.out.println("Get State"+userArray.get(0).getBrand());

                RecyclerView recyclerView = findViewById(R.id.recyclebrowse);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(new ProductsAdapter(userArray));

            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                System.out.println("Nhi ho rha");
                System.out.println(""+t);

            }
        });





    }
}