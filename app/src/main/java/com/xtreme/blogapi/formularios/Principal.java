package com.xtreme.blogapi.formularios;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.xtreme.blogapi.MainActivity;
import com.xtreme.blogapi.R;
import com.xtreme.blogapi.modelos.Post;
import com.xtreme.blogapi.servicios.PostClient;

import com.xtreme.blogapi.servicios.UserClient;
import com.xtreme.blogapi.utilitarios.RowAdapter;
import com.xtreme.blogapi.utilitarios.api;


import java.util.ArrayList;
import java.util.List;


public class Principal extends AppCompatActivity {

    private static final String TAG="blogapi";
    private PostClient blogApi;
    private UserClient ublogapi;
    private String token;
    private RecyclerView recyclerView;
    private  SharedPreferences preferences;
    private  Retrofit retrofit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        token = preferences.getString("token","No existe");
        Log.i(TAG, "Mi token en Sharepreferens es: "+token);


        retrofit = api.getApi();
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
                String  idpost = String.valueOf(list.get(recyclerView.getChildAdapterPosition(v)).getId());
                Intent viewpost = new Intent (v.getContext(), ViewPost.class);
                viewpost.putExtra("idpost",idpost);
                startActivityForResult(viewpost,0);
            }
        });
        recyclerView.setAdapter(rowAdapter);
    }

    public void logOut(){

        retrofit = api.getApi();
        ublogapi = retrofit.create(UserClient.class);

        Call<ResponseBody> call = ublogapi.logoutUser("Bearer "+token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.code()==200){

                    preferences.edit().remove("id").commit();
                    preferences.edit().remove("nombre").commit();
                    preferences.edit().remove("email").commit();
                    preferences.edit().remove("tokern").commit();
                    preferences.edit().clear();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "No se han podido cerrar la sesión", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }

  @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.principal_btn_add_post:
                Toast.makeText(this, "Item 1 selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.principal_btn_view_post:
                Toast.makeText(this, "Item 2 selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.principal_btn_logout:
                logOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}


