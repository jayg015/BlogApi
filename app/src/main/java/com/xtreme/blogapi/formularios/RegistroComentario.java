package com.xtreme.blogapi.formularios;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.xtreme.blogapi.R;
import com.xtreme.blogapi.modelos.Post;
import com.xtreme.blogapi.servicios.PostClient;
import com.xtreme.blogapi.servicios.UserClient;
import com.xtreme.blogapi.utilitarios.api;

public class AddComment extends AppCompatActivity {

    private static final String TAG="XXXXXXX - Add Comment";
    private Button add;
    private EditText detalle;
    private PostClient blogApi;
    private UserClient ublogapi;
    private String token, codigopost;
    private SharedPreferences preferences;
    private Retrofit retrofit;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment);

        add=findViewById(R.id.addcomment_btn_agregar);
        detalle=findViewById(R.id.addcomment_txt_comment);
        codigopost = getIntent().getExtras().getString("idpost");


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
                token = preferences.getString("token","No existe");
                Log.i(TAG, "token en view post "+ token);

                retrofit = api.getApi();
                blogApi = retrofit.create(PostClient.class);

                Call<Void> call =blogApi.addComment("Bearer "+token,detalle.getText().toString(),Integer.valueOf(codigopost));

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (!response.isSuccessful()) {
                            Log.i(TAG, "Respuesta no fue buena el codigo de error es "+response.code() );
                            return;
                        }else {
                            Log.i(TAG, "Respuesta  fue buena el codigo  es "+response.code() );
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });


            }
        });

    }
}
