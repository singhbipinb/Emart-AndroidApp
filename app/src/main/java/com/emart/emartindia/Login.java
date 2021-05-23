package com.emart.emartindia;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.emart.emartindia.apiclient.ProductInterface;
import com.emart.emartindia.apiclient.UserInterface;
import com.emart.emartindia.apiclient.apiClient;
import com.emart.emartindia.models.Users;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Login extends AppCompatActivity {

    private Retrofit retrofit;
    Gson gson = new Gson();
    private ProductInterface productInterface;
    private com.emart.emartindia.apiclient.apiClient apiClient;
    EditText email, pass, fullname, emailregister, passregister;
    TextView msglogin,msgregister;
    ProgressBar loading;

    SharedPreferences LoginToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        fullname = findViewById(R.id.nameregister);
        emailregister = findViewById(R.id.emailregister);
        passregister = findViewById(R.id.passwordregister);
        msglogin = findViewById(R.id.loginerror);
        msgregister = findViewById(R.id.registererror);
        loading = findViewById(R.id.loading);


    }


    public void Login(){

        findViewById(R.id.loginpage).setVisibility(View.INVISIBLE);
        loading.setVisibility(View.VISIBLE);


        final UserInterface apicall = apiClient.getClient().create(UserInterface.class);


        Users user = new Users();

        user.setEmail(email.getText().toString());
        user.setPassword(pass.getText().toString());


        Call<Users> call = apicall.AuthUser(user);

        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {

                loading.setVisibility(View.GONE);
                findViewById(R.id.loginpage).setVisibility(View.VISIBLE);

                if(response.body()!=null){

                LoginToken = getSharedPreferences("LoginData",MODE_PRIVATE);

                SharedPreferences.Editor myEditor = LoginToken.edit();

                myEditor.putString("authtoken",response.body().getAuthToken());

                myEditor.apply();

                System.out.println("Authtoken "+response.body().getAuthToken());

                    msglogin.setText("Login Successful");

                }

                else {
                    loading.setVisibility(View.GONE);
                    findViewById(R.id.loginpage).setVisibility(View.VISIBLE);
                    msglogin.setText("Wrong Credential");
                }

            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {

                System.out.println("Unable to login");

            }
        });


    }


    public void Register(){

        findViewById(R.id.registerpage).setVisibility(View.INVISIBLE);
        loading.setVisibility(View.VISIBLE);

        boolean Result = false;

        final UserInterface apicall = apiClient.getClient().create(UserInterface.class);


        Users user = new Users();

        user.setName(fullname.getText().toString());
        user.setEmail(emailregister.getText().toString());
        user.setPassword(passregister.getText().toString());


        Call<Users> call = apicall.CreateUser(user);

        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {

                if(response.body()!=null){

                    loading.setVisibility(View.GONE);
                    findViewById(R.id.registerpage).setVisibility(View.VISIBLE);

                    LoginToken = getSharedPreferences("LoginData",MODE_PRIVATE);

                    SharedPreferences.Editor myEditor = LoginToken.edit();

                    myEditor.putString("authtoken",response.body().getAuthToken());

                    myEditor.apply();

                    System.out.println("Authtoken "+response.body().getEmail());

                    msgregister.setText("Registered Successfully");

                }

                else {
                    loading.setVisibility(View.GONE);
                    findViewById(R.id.registerpage).setVisibility(View.VISIBLE);
                    msgregister.setText("User Already Exists");
                }

            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {

                System.out.println("Unable to login"+t.toString());
                loading.setVisibility(View.GONE);
                findViewById(R.id.registerpage).setVisibility(View.VISIBLE);

            }
        });


    }

    public void authUser(View view) {

        Login();
    }

    public void gotoRegister(View view) {

        findViewById(R.id.loginpage).setVisibility(View.INVISIBLE);

        findViewById(R.id.registerpage).setVisibility(View.VISIBLE);


    }

    public void gotoLogin(View view) {
        findViewById(R.id.loginpage).setVisibility(View.VISIBLE);

        findViewById(R.id.registerpage).setVisibility(View.INVISIBLE);
    }

    public void addUser(View view) {

        Register();
    }
}