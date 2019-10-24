package com.xtreme.blogapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.xtreme.blogapi.formularios.Principal;
import com.xtreme.blogapi.modelos.Login;
import com.xtreme.blogapi.modelos.User;
import com.xtreme.blogapi.servicios.UserClient;
import com.xtreme.blogapi.formularios.Registro;
import com.xtreme.blogapi.utilitarios.api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private static final String TAG="blogapi";
    private EditText edittextusername;
    private EditText edittextpassword;
    private Button btn_login;
    private Button btn_registro;
    private UserClient blogApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        edittextusername = findViewById(R.id.login_txt_username);
        edittextpassword = findViewById(R.id.login_txt_password);
        btn_login = findViewById(R.id.login_btn_login);
        btn_registro = findViewById(R.id.login_btn_registrar);

       Retrofit retrofit = api.getApi();
       blogApi = retrofit.create(UserClient.class);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                String email = edittextusername.getText().toString();
                String password = edittextpassword.getText().toString();

                Login login = new Login(email,password);
                Call<User> call = blogApi.login(login);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Log.i(TAG, "paso");
                        if(response.isSuccessful()){

                            SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putInt("id", response.body().getId());
                            editor.putString("nombre", response.body().getName());
                            editor.putString("email", response.body().getEmail());
                            editor.putString("token", response.body().getToken());
                            editor.commit();

                            Intent principtal = new Intent (v.getContext(), Principal.class);
                            startActivityForResult(principtal, 0);

                        }else{
                            Log.i(TAG, "Credenciales invalidas");
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(MainActivity.this,"Error :(",Toast.LENGTH_SHORT).show();
                        Log.i(TAG, "No funciono");
                    }
                });
            }
        });

        btn_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registro_activity = new Intent (v.getContext(), Registro.class);
                startActivityForResult(registro_activity, 0);

            }
        });



    }
}
