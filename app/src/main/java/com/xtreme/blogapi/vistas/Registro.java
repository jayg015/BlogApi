package com.xtreme.blogapi.vistas;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.xtreme.blogapi.MainActivity;
import com.xtreme.blogapi.R;
import com.xtreme.blogapi.modelos.User;
import com.xtreme.blogapi.servicios.UserClient;

public class Registro extends AppCompatActivity {

    private static final String TAG="blogapi";
    private String nombre;
    private String email;
    private String password;
    Button btnlogin;
    private UserClient blogApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        nombre="Marcos";
        email="mgblogapi@blogapi.com";
        password="123456";
        btnlogin=findViewById(R.id.registrar_btn_registrar);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre="Mateo Peralta";
                email="mpblogapi@blogapi.com";
                password="123";

                User user = new User(nombre,email,password);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://itla.hectorvent.com/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                blogApi = retrofit.create(UserClient.class);
                Call<User> call = blogApi.register(user);

                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Toast.makeText(Registro.this,"Te Salvaste",Toast.LENGTH_SHORT).show();
                        Log.i(TAG, "Me salve");
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(Registro.this,"Error :(",Toast.LENGTH_SHORT).show();
                        Log.i(TAG, "Me jodi");
                    }
                });

            }
        });


    }
}
