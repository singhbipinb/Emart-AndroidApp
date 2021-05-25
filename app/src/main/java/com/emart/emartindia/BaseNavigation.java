package com.emart.emartindia;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.emart.emartindia.models.Orders;
import com.google.android.material.navigation.NavigationView;

public class BaseNavigation extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;

    protected DrawerLayout drawerLayout;

    protected FrameLayout frameLayout;
    SharedPreferences LoginToken;
    String name, email;
    TextView tv1,tv2;
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

        LoginToken = getSharedPreferences("LoginData",MODE_PRIVATE);

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


        loginStatus();



    }

    public void loginStatus(){

        System.out.println("LoignStatusclas");

        View view = navigationView.getHeaderView(0);

        login = view.findViewById(R.id.navheaderlogin);
        loggedin = view.findViewById(R.id.navheaderalready);

        tv1 = view.findViewById(R.id.navheadername);
        tv2 = view.findViewById(R.id.navheaderemail);
        System.out.println("Auth name"+LoginToken.getString("authtoken",""));

        if(LoginToken.getString("authtoken","").isEmpty()){

            System.out.println("Empty hai");

            login.setVisibility(View.VISIBLE);
            loggedin.setVisibility(View.INVISIBLE);

        }
        else {

            name = LoginToken.getString("authname", "");
            email = LoginToken.getString("authemail", "");
            tv1.setText(""+name);
            tv2.setText(""+email);
            login.setVisibility(View.INVISIBLE);
            loggedin.setVisibility(View.VISIBLE);

        }

        view.findViewById(R.id.navtologin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                overridePendingTransition(R.transition.fadein,R.transition.fadeout);
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected( MenuItem item) {

        int id = item.getItemId();

        String loginto = LoginToken.getString("authtoken","");

        switch (id){

            case R.id.myorders:
                if(loginto.isEmpty()){
                    Intent intent = new Intent(this, Login.class);
                    startActivity(intent);

                }
                else {
                    Intent intent = new Intent(this, MyOrders.class);
                    startActivity(intent);

                }
                break;

            case R.id.mycart:
                if(loginto.isEmpty()){
                    Intent intent = new Intent(this, Login.class);
                    startActivity(intent);

                }
                else {
                    Intent intent2 = new Intent(this, Cart.class);
                    startActivity(intent2);

                }
                break;

            case R.id.myprofile:
                if(loginto.isEmpty()){
                    Intent intent = new Intent(this, Login.class);
                    startActivity(intent);

                }
                else {
                    Intent intent3 = new Intent(this, Profile.class);
                    startActivity(intent3);

                }
                break;

            case R.id.categoty:
                if(loginto.isEmpty()){
                    Intent intent = new Intent(this, Login.class);
                    startActivity(intent);

                }
                else {
                    Intent intent3 = new Intent(this, Browse.class);
                    startActivity(intent3);

                }
                break;

            case R.id.logout:
                if(loginto.isEmpty()){

                }
                else {
                    LogOut();
                    findViewById(R.id.logout).setVisibility(View.INVISIBLE);
                }
                break;

            case R.id.home:
                Intent intent3 = new Intent(this, MainActivity.class);
                startActivity(intent3);
                break;







        }

        System.out.println("Item Clicked "+item.getItemId());


        return false;
    }

    private void LogOut() {

        SharedPreferences.Editor editor = LoginToken.edit();

        editor.clear().apply();

        drawerLayout.closeDrawer(GravityCompat.START);

        loginStatus();


    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}