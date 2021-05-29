package com.emart.emartindia;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class BaseNavigation extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    protected DrawerLayout drawerLayout;
    protected FrameLayout frameLayout;
    SharedPreferences LoginToken;
    String name, email;
    TextView tv1, tv2;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private LinearLayout login, loggedin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_navigation);

        toolbar = findViewById(R.id.maintoolbar);

        drawerLayout = findViewById(R.id.drawerlayout);

        navigationView = findViewById(R.id.navview);
        frameLayout = findViewById(R.id.content_frame);

        LoginToken = getSharedPreferences("LoginData", MODE_PRIVATE);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.opennavigation, R.string.closenavigation
        );

        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        loginStatus();


        findViewById(R.id.toolbarcart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Cart.class);
                startActivity(intent);
            }
        });


    }

    public void loginStatus() {


        View view = navigationView.getHeaderView(0);
        Menu menu = navigationView.getMenu();

        login = view.findViewById(R.id.navheaderlogin);
        loggedin = view.findViewById(R.id.navheaderalready);

        tv1 = view.findViewById(R.id.navheadername);
        tv2 = view.findViewById(R.id.navheaderemail);

        if (LoginToken.getString("authtoken", "").isEmpty()) {


            login.setVisibility(View.VISIBLE);
            loggedin.setVisibility(View.INVISIBLE);
            MenuItem item = menu.getItem(5);

            item.setVisible(false);

        } else {

            name = LoginToken.getString("authname", "");
            email = LoginToken.getString("authemail", "");
            tv1.setText("" + name);
            tv2.setText("" + email);
            login.setVisibility(View.INVISIBLE);
            loggedin.setVisibility(View.VISIBLE);


        }

        view.findViewById(R.id.navtologin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                overridePendingTransition(R.transition.fadein, R.transition.fadeout);
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        String loginto = LoginToken.getString("authtoken", "");

        switch (id) {

            case R.id.myorders:
                if (loginto.isEmpty()) {
                    Intent intent = new Intent(this, Login.class);
                    startActivity(intent);

                } else {
                    Intent intent = new Intent(this, MyOrders.class);
                    startActivity(intent);

                }
                break;

            case R.id.mycart:
                if (loginto.isEmpty()) {
                    Intent intent = new Intent(this, Login.class);
                    startActivity(intent);

                } else {
                    Intent intent2 = new Intent(this, Cart.class);
                    startActivity(intent2);

                }
                break;

            case R.id.myprofile:
                if (loginto.isEmpty()) {
                    Intent intent = new Intent(this, Login.class);
                    startActivity(intent);

                } else {
                    Intent intent3 = new Intent(this, Profile.class);
                    startActivity(intent3);

                }
                break;

            case R.id.categoty:
//                if(loginto.isEmpty()){
//                    Intent intent = new Intent(this, Login.class);
//                    startActivity(intent);
//
//                }
//                else {
                Intent intent3 = new Intent(this, Categories.class);
                startActivity(intent3);

//                }
                break;

            case R.id.logout:
                if (loginto.isEmpty()) {

                } else {
                    LogOut();
                    findViewById(R.id.logout).setVisibility(View.GONE);
                    Intent intent4 = new Intent(this, MainActivity.class);
                    startActivity(intent4);

                }
                break;

            case R.id.home:
                Intent intent4 = new Intent(this, MainActivity.class);
                startActivity(intent4);
                break;

            case R.id.contact:
                Intent intent5 = new Intent(this, ContactActivity.class);
                startActivity(intent5);
                break;


        }


        return false;
    }

    private void LogOut() {

        SharedPreferences.Editor editor = LoginToken.edit();

        editor.clear();

        if (editor.commit()) {
            drawerLayout.closeDrawer(GravityCompat.START);
            loginStatus();
        }


    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}