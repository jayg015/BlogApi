package com.xtreme.blogapi.formularios;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.xtreme.blogapi.R;
import com.xtreme.blogapi.modelos.Post;
import com.xtreme.blogapi.servicios.PostClient;

import com.xtreme.blogapi.utilitarios.RowAdapter;
import com.xtreme.blogapi.utilitarios.api;


import java.util.ArrayList;
import java.util.List;


public class Principal extends AppCompatActivity {

    private static final String TAG="blogapi";
    private PostClient blogApi;
    private String token;
     private RecyclerView recyclerView;


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
                }else {
                     {
                         List<Post>posts=response.body();
                         getPost(posts);
                     }
                }

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.i(TAG, "Respuesta fue "+ t.getMessage());
            }
        });

    }


    public void getPost(List<Post> posts){
        recyclerView= findViewById(R.id.principal_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        final ArrayList<Post> list = new ArrayList<>();

        for(Post post:posts){
            Post addpost=new Post();
            addpost.setId(post.getId());
            addpost.setTitulo("Titulo: "+ post.getTitle());
            addpost.setText("Detalle: \n\n"+post.getText()+"\n");
            list.add(addpost);
        }

        RowAdapter rowAdapter = new RowAdapter(list);
        rowAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Seleccion: "+list.get(recyclerView.getChildAdapterPosition(v)).getId(),Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(rowAdapter);
    }

}
