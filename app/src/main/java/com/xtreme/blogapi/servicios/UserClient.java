package com.xtreme.blogapi.servicios;

import com.xtreme.blogapi.modelos.Login;
import com.xtreme.blogapi.modelos.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserClient {

    @POST("login")
    Call<User> login(@Body Login login);

    @POST("register")
    Call<User> register(@Body User user);

    @GET("me")
    Call<User> me (@Header("Athorization") String token);

    @DELETE("logout")
    Call<ResponseBody> logoutUser(@Header("Authorization") String token);
}
