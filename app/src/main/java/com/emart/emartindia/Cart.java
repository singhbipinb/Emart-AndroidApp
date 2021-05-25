package com.emart.emartindia;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class Cart extends BaseNavigation {


    SQLiteDatabase mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.transition.fadein, R.transition.fadeout);
        LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.activity_cart,null,false);

        frameLayout.addView(view);


        mydb = openOrCreateDatabase("cartdb",MODE_PRIVATE,null);



        showCartItems();




    }


    public void showCartItems(){

        Cursor result = mydb.rawQuery("select * from MyCart",null);


        if(result.moveToFirst()){
            LinearLayout reviLi = findViewById(R.id.cartLinearLayout);

            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

            do{




                View view = inflater.inflate(R.layout.cartlayout,null);

                ImageView img = view.findViewById(R.id.cartitemimage);

                System.out.println("Img "+result.getString(2));

                Glide.with(getApplicationContext()).load(result.getString(2)).into(img);


                TextView tv1 = view.findViewById(R.id.cartitemname);

                tv1.setText(""+result.getString(1));


                TextView tv2 = view.findViewById(R.id.cartitemprice);

                tv2.setText(""+result.getDouble(3));


                Spinner tv3 = (Spinner) view.findViewById(R.id.cartitemqty);

                List<Integer> stock = new ArrayList<>();
                for (int i=1;i<=result.getInt(4)/10;i++){
                    stock.add(i);
                }

                ArrayAdapter<Integer> qtyAdapter= new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item,stock);

                qtyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                tv3.setAdapter(qtyAdapter);

                tv3.setSelection(result.getInt(5)-1);
//                    tv3.setText(""+response.body().getOrderItems()[i].getQuantity());

                ImageButton delete= view.findViewById(R.id.deletefromcart);

                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String query = "delete from MyCart where name= '"+tv1.getText()+"'";
                        mydb.execSQL(query);


                    }
                });


                reviLi.addView(view);



            }
            while (result.moveToNext());



        }


    }


    public void Checkout(View view) {




    }
}