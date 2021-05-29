package com.emart.emartindia;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

public class Categories extends BaseNavigation {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Categories");
        overridePendingTransition(R.transition.fadein, R.transition.fadeout);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.activity_categories, null, false);
        frameLayout.addView(view);


    }

    public void gotoSmartphones(View view) {

        Intent intent = new Intent(this, SubCategories.class);
        intent.putExtra("category", "Smartphones");
        startActivity(intent);


    }

    public void gotoComputer(View view) {
        Intent intent = new Intent(this, SubCategories.class);
        intent.putExtra("category", "Computers");
        startActivity(intent);
    }

    public void gotoGadgets(View view) {
        Intent intent = new Intent(this, SubCategories.class);
        intent.putExtra("category", "Gadgets");
        startActivity(intent);
    }

    public void gotoHomeApp(View view) {
        Intent intent = new Intent(this, SubCategories.class);
        intent.putExtra("category", "HomeAppliances");
        startActivity(intent);
    }
}