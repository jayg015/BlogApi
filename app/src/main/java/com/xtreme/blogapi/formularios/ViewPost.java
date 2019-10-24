package com.xtreme.blogapi.formularios;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Retrofit;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.xtreme.blogapi.R;
import com.xtreme.blogapi.servicios.PostClient;
import com.xtreme.blogapi.utilitarios.api;

public class ViewPost extends AppCompatActivity {

    private static final String TAG="blogapi";
    private PostClient blogApi;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_post);

        /*SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        token = preferences.getString("token","No existe");
        Log.i(TAG, "Mi token en Sharepreferens es: "+token);


        Retrofit retrofit = api.getApi();
        blogApi = retrofit.create(PostClient.class);*/

        String id = getIntent().getExtras().getString("idpost");



        Toast.makeText(this, "El id del post es: "+id, Toast.LENGTH_SHORT).show();
    }
}
