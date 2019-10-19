package com.xtreme.blogapi.servicios;

import com.xtreme.blogapi.modelos.Login;
import com.xtreme.blogapi.modelos.Post;


import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface PostClient {
    @GET("post")
    Call<List<Post>> getPost(@Header("Authorization") String token);

}
