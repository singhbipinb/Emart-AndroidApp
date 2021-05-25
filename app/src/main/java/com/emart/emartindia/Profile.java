package com.emart.emartindia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.emart.emartindia.apiclient.UserInterface;
import com.emart.emartindia.apiclient.apiClient;
import com.emart.emartindia.models.Users;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile extends BaseNavigation {

    SharedPreferences LoginToken;

    EditText fullname,emailupdate, passupdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.transition.fadein, R.transition.fadeout);
        LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.activity_profile,null,false);
        frameLayout.addView(view);

        fullname = (EditText) findViewById(R.id.nameprofile);
        emailupdate = (EditText)findViewById(R.id.emailprofile);
        passupdate = (EditText)findViewById(R.id.passwordprofile);


        LoginToken = getSharedPreferences("LoginData",MODE_PRIVATE);

        String s1 = LoginToken.getString("authname","");
        String s2 = LoginToken.getString("authemail","");

        fullname.setText(s1);
        emailupdate.setText(s2);




    }

    public void UpdateProfile(View view) {


        final UserInterface apicall = apiClient.getClient().create(UserInterface.class);


        Users user = new Users();

        user.setName(fullname.getText().toString());
        user.setEmail(emailupdate.getText().toString());
        user.setPassword(passupdate.getText().toString());


        Call<Users> call = apicall.UpdateUser("Bearer "+LoginToken.getString("authtoken",""),user);

        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {

                if(response.body()!=null){

//                    loading.setVisibility(View.GONE);
//                    findViewById(R.id.registerpage).setVisibility(View.VISIBLE);
//
                    LoginToken = getSharedPreferences("LoginData",MODE_PRIVATE);

                    SharedPreferences.Editor myEditor = LoginToken.edit();

                    myEditor.putString("authtoken",response.body().getAuthToken());
                    myEditor.putString("authname",response.body().getName());
                    myEditor.putString("authemail",response.body().getEmail());

                    if(myEditor.commit()){
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }
//
//                    System.out.println("Authtoken "+response.body().getEmail());
//
//                    msgregister.setText("Registered Successfully");

                    System.out.println("Profile Updated");


                }

                else {
//                    loading.setVisibility(View.GONE);
//                    findViewById(R.id.registerpage).setVisibility(View.VISIBLE);
//                    msgregister.setText("User Already Exists");
                }

            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {

                System.out.println("Unable to login"+t.toString());
//                loading.setVisibility(View.GONE);
//                findViewById(R.id.registerpage).setVisibility(View.VISIBLE);

            }
        });


    }
}