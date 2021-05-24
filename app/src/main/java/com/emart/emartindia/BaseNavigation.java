package com.emart.emartindia;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.emart.emartindia.models.Orders;
import com.google.android.material.navigation.NavigationView;

public class BaseNavigation extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;

    protected DrawerLayout drawerLayout;

    protected FrameLayout frameLayout;

    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_navigation);

        toolbar = findViewById(R.id.maintoolbar);

        drawerLayout = findViewById(R.id.drawerlayout);

        navigationView = findViewById(R.id.navview);
        frameLayout = findViewById(R.id.content_frame);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.opennavigation,R.string.closenavigation
        );

        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);



    }

    @Override
    public boolean onNavigationItemSelected( MenuItem item) {

        int id = item.getItemId();

        switch (id){

            case R.id.myorders:
                Intent intent = new Intent(this, MyOrders.class);
                startActivity(intent);
                overridePendingTransition(R.transition.fadein,R.transition.fadeout);
                break;

            case R.id.mycart:
                Intent intent2 = new Intent(this, Cart.class);
                startActivity(intent2);
                overridePendingTransition(R.transition.fadein,R.transition.fadeout);
                break;

            case R.id.myprofile:
                break;

            case R.id.categoty:
                Intent intent3 = new Intent(this, Browse.class);
                startActivity(intent3);
                overridePendingTransition(R.transition.fadein,R.transition.fadeout);
                break;





        }

        System.out.println("Item Clicked "+item.getItemId());


        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}