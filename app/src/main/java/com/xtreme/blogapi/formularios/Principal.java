package com.xtreme.blogapi.formularios;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;


import com.xtreme.blogapi.R;
import com.xtreme.blogapi.modelos.Post;
import com.xtreme.blogapi.servicios.PostClient;

import com.xtreme.blogapi.utilitarios.api;


import java.util.List;


public class Principal extends AppCompatActivity {

    private static final String TAG="blogapi";
    private PostClient blogApi;
    private String token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        token = preferences.getString("token","No existe");
        Log.i(TAG, "Mi token en Sharepreferens es: "+token);


        Retrofit retrofit = api.getApi();
        blogApi = retrofit.create(PostClient.class);

        Call<List<Post>> call = blogApi.getPost("Bearer "+token);
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    Log.i(TAG, "Respuesta no fue buena");
                    return;
                }else{

                    Log.i(TAG, "Respuesta fue buena");

                }

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.i(TAG, "Respuesta fue "+ t.getMessage());
            }
        });

    }
}
