package com.xtreme.blogapi.vistas;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

    private EditText edittextusername;
    private EditText edittextpassword;
    private EditText edittextconfirmar;
    private EditText edittextemail;

    Button btnlogin;
    private UserClient blogApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        edittextusername = findViewById(R.id.registrar_txt_nombre);
        edittextemail = findViewById(R.id.registrar_txt_email);
        edittextpassword = findViewById(R.id.registrar_txt_password);

        btnlogin=findViewById(R.id.registrar_btn_registrar);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                    nombre = edittextusername.getText().toString();
                    email = edittextemail.getText().toString();
                    password= edittextpassword.getText().toString();

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
                            Intent main = new Intent(v.getContext(), MainActivity.class);
                            startActivityForResult(main, 0);
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
